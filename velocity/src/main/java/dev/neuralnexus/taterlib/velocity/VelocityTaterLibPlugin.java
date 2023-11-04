package dev.neuralnexus.taterlib.velocity;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import dev.neuralnexus.taterlib.common.Constants;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.command.TaterLibCommand;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.velocity.event.api.server.VelocityServerStartedEvent;
import dev.neuralnexus.taterlib.velocity.event.api.server.VelocityServerStoppedEvent;
import dev.neuralnexus.taterlib.velocity.logger.VelocityLogger;
import dev.neuralnexus.taterlib.velocity.commands.VelocityTaterLibCommand;
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
        id = Constants.PROJECT_ID,
        name = Constants.PROJECT_NAME,
        version = Constants.PROJECT_VERSION,
        authors = Constants.PROJECT_AUTHORS,
        description = Constants.PROJECT_DESCRIPTION,
        url = Constants.PROJECT_URL,
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
        proxyServer = server;
        pluginStart(this, new VelocityLogger(logger));
        TaterLib.configFolder = "config";
        TaterLib.serverType = "Velocity";
        TaterLib.minecraftVersion = server.getVersion().getVersion();

        // Register LuckPerms hook
        if (server.getPluginManager().getPlugin("LuckPerms").isPresent()) {
            TaterLib.logger.info("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook("luckperms", new LuckPermsHook());
        }

        // Register commands
        CommandManager commandManager = server.getCommandManager();
        commandManager.register(TaterLibCommand.getCommandName(), new VelocityTaterLibCommand());

        EventManager eventManager = server.getEventManager();

        // Register player listeners
        eventManager.register(this, new VelocityPlayerListener());

        // Register plugin message listener
        eventManager.register(this, new VelocityPluginMessageListener());

        // Register server listeners
        server.getScheduler().buildTask(this, () -> ServerEvents.STARTED.invoke(new VelocityServerStartedEvent())).delay(Duration.ofSeconds(5)).schedule();
        eventManager.register(this, new VelocityServerListener());

        TaterLib.setRegisterChannels((channels) -> channels.forEach((channel) -> server.getChannelRegistrar().register(MinecraftChannelIdentifier.from(channel))));
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
