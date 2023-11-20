package dev.neuralnexus.taterlib.velocity;

import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Dependency;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.event.api.PluginMessageEvents;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.velocity.event.command.VelocityBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.event.command.VelocityCommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.event.pluginmessages.VelocityRegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.velocity.event.server.VelocityServerStartedEvent;
import dev.neuralnexus.taterlib.velocity.event.server.VelocityServerStoppedEvent;
import dev.neuralnexus.taterlib.velocity.logger.VelocityLogger;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.neuralnexus.taterlib.velocity.listeners.player.VelocityPlayerListener;
import dev.neuralnexus.taterlib.velocity.listeners.pluginmessages.VelocityPluginMessageListener;
import dev.neuralnexus.taterlib.velocity.listeners.server.VelocityServerListener;
import dev.neuralnexus.taterlib.velocity.server.VelocityProxyServer;
import org.slf4j.Logger;

import java.time.Duration;

/**
 * Velocity entry point.
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
    private static ProxyServer server;

    /**
     * Gets the proxy server.
     * @return The proxy server.
     */
    public static ProxyServer getProxyServer() {
        return server;
    }

    @Inject
    public VelocityTaterLibPlugin(ProxyServer server, Logger logger) {
        VelocityTaterLibPlugin.server = server;

        TaterAPIProvider.register("plugins", server.getVersion().getVersion());
        pluginStart(server, new VelocityLogger(logger));
        TaterAPI api = TaterAPIProvider.get();
        api.setIsPluginLoaded((plugin) -> server.getPluginManager().getPlugin(plugin).isPresent());
        api.setServer(() -> new VelocityProxyServer(server));
    }

    /**
     * Called when the proxy is initialized.
     * @param event The event.
     */
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        // Register listeners
        EventManager eventManager = server.getEventManager();
        eventManager.register(this, new VelocityPlayerListener());
        eventManager.register(this, new VelocityPluginMessageListener());
        eventManager.register(this, new VelocityServerListener());

        server.getScheduler().buildTask(this, () -> {
            // Register commands
            CommandEvents.REGISTER_COMMAND.invoke(new VelocityCommandRegisterEvent());
            CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(new VelocityBrigadierCommandRegisterEvent());

            // Register plugin messages
            PluginMessageEvents.REGISTER_PLUGIN_MESSAGES.invoke(new VelocityRegisterPluginMessagesEvent());

            // Fire server started event
            ServerEvents.STARTED.invoke(new VelocityServerStartedEvent());
        }).delay(Duration.ofSeconds(5)).schedule();
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
