package dev.neuralnexus.taterlib.velocity;

import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.velocity.event.api.command.VelocityCommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.event.api.server.VelocityServerStartedEvent;
import dev.neuralnexus.taterlib.velocity.event.api.server.VelocityServerStoppedEvent;
import dev.neuralnexus.taterlib.velocity.logger.VelocityLogger;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.neuralnexus.taterlib.velocity.listeners.player.VelocityPlayerListener;
import dev.neuralnexus.taterlib.velocity.listeners.pluginmessages.VelocityPluginMessageListener;
import dev.neuralnexus.taterlib.velocity.listeners.server.VelocityServerListener;
import org.slf4j.Logger;

import java.time.Duration;

/**
 * The TaterLib Velocity plugin.
 */
@Plugin(
        id = TaterLib.Constants.PROJECT_ID,
        name = TaterLib.Constants.PROJECT_NAME,
        version = TaterLib.Constants.PROJECT_VERSION,
        authors = TaterLib.Constants.PROJECT_AUTHORS,
        description = TaterLib.Constants.PROJECT_DESCRIPTION,
        url = TaterLib.Constants.PROJECT_URL,
        dependencies = {
                @Dependency(id = "luckperms", optional = true)
        }
)
public class VelocityTaterLibPlugin implements TaterLibPlugin {
    @Inject private ProxyServer server;
    @Inject private Logger logger;

    private static ProxyServer proxyServer;

    /**
     * Gets the proxy server.
     * @return The proxy server.
     */
    public static ProxyServer getProxyServer() {
        return proxyServer;
    }

    /**
     * Called when the proxy is initialized.
     * @param event The event.
     */
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        TaterAPIProvider.register("plugins", server.getVersion().getVersion());
        pluginStart(this, new VelocityLogger(logger));
        TaterAPI api = TaterAPIProvider.get();

        proxyServer = server;

        // Register LuckPerms hook
        if (server.getPluginManager().getPlugin("LuckPerms").isPresent()) {
            TaterLib.getLogger().info("LuckPerms detected, enabling LuckPerms hook.");
            api.addHook("luckperms", new LuckPermsHook());
        }

        // Register command events
        server.getScheduler().buildTask(this, () -> CommandEvents.REGISTER_COMMAND.invoke(new VelocityCommandRegisterEvent())).delay(Duration.ofSeconds(5)).schedule();

        EventManager eventManager = server.getEventManager();

        // Register player listeners
        eventManager.register(this, new VelocityPlayerListener());

        // Register plugin message listener
        eventManager.register(this, new VelocityPluginMessageListener());

        // Register server listeners
        server.getScheduler().buildTask(this, () -> ServerEvents.STARTED.invoke(new VelocityServerStartedEvent())).delay(Duration.ofSeconds(5)).schedule();
        eventManager.register(this, new VelocityServerListener());

        api.setRegisterChannels((channels) -> channels.forEach((channel) -> server.getChannelRegistrar().register(MinecraftChannelIdentifier.from(channel))));
    }

    /**
     * Called when the proxy is shutting down.
     * @param event The event.
     */
    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        ServerEvents.STOPPED.invoke(new VelocityServerStoppedEvent());
        pluginStop();
    }
}
