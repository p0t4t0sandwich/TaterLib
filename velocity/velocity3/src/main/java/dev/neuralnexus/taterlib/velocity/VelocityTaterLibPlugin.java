package dev.neuralnexus.taterlib.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.api.PluginMessageEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.velocity.event.command.VelocityBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.event.command.VelocityCommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.event.pluginmessages.VelocityRegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.velocity.event.server.VelocityServerStartedEvent;
import dev.neuralnexus.taterlib.velocity.event.server.VelocityServerStoppedEvent;
import dev.neuralnexus.taterlib.velocity.hooks.permissions.VelocityPermissionsHook;
import dev.neuralnexus.taterlib.velocity.listeners.player.VelocityPlayerListener;
import dev.neuralnexus.taterlib.velocity.listeners.pluginmessages.VelocityPluginMessageListener;
import dev.neuralnexus.taterlib.velocity.listeners.server.VelocityServerListener;
import dev.neuralnexus.taterlib.velocity.server.VelocityProxyServer;

import org.slf4j.Logger;

import java.time.Duration;

/** Velocity entry point. */
@Plugin(
        id = TaterLib.Constants.PROJECT_ID,
        name = TaterLib.Constants.PROJECT_NAME,
        version = TaterLib.Constants.PROJECT_VERSION,
        authors = TaterLib.Constants.PROJECT_AUTHORS,
        description = TaterLib.Constants.PROJECT_DESCRIPTION,
        url = TaterLib.Constants.PROJECT_URL,
        dependencies = {@Dependency(id = "luckperms", optional = true)})
public class VelocityTaterLibPlugin implements TaterLibPlugin {
    private static ProxyServer server;

    @Inject
    public VelocityTaterLibPlugin(ProxyServer server, Logger logger) {
        VelocityTaterLibPlugin.server = server;

        TaterAPIProvider.register(server.getVersion().getVersion());
        TaterAPIProvider.addHook(new VelocityPermissionsHook());
        pluginStart(server, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.VELOCITY);
        api.setIsPluginLoaded((plugin) -> server.getPluginManager().getPlugin(plugin).isPresent());
        api.setServer(() -> new VelocityProxyServer(server));
    }

    /**
     * Gets the proxy server.
     *
     * @return The proxy server.
     */
    public static ProxyServer getProxyServer() {
        return server;
    }

    /**
     * Called when the proxy is initialized.
     *
     * @param event The event.
     */
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());

        // Register listeners
        EventManager eventManager = server.getEventManager();
        eventManager.register(this, new VelocityPlayerListener());
        eventManager.register(this, new VelocityPluginMessageListener());
        eventManager.register(this, new VelocityServerListener());

        server.getScheduler()
                .buildTask(
                        this,
                        () -> {
                            // Register commands
                            CommandEvents.REGISTER_COMMAND.invoke(
                                    new VelocityCommandRegisterEvent());
                            CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                                    new VelocityBrigadierCommandRegisterEvent());

                            // Register plugin messages
                            PluginMessageEvents.REGISTER_PLUGIN_MESSAGES.invoke(
                                    new VelocityRegisterPluginMessagesEvent());

                            // Fire server started event
                            ServerEvents.STARTED.invoke(new VelocityServerStartedEvent());
                        })
                .delay(Duration.ofSeconds(5))
                .schedule();
    }

    /**
     * Called when the proxy is shutting down.
     *
     * @param event The event.
     */
    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        ServerEvents.STOPPED.invoke(new VelocityServerStoppedEvent());
        pluginStop();
    }
}
