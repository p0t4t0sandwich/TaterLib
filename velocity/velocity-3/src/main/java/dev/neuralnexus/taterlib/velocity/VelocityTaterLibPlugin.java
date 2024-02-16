package dev.neuralnexus.taterlib.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.velocity.event.command.VelocityBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.event.command.VelocityCommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.event.network.VelocityRegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.velocity.event.server.VelocityServerStartedEvent;
import dev.neuralnexus.taterlib.velocity.event.server.VelocityServerStoppedEvent;
import dev.neuralnexus.taterlib.velocity.hooks.permissions.VelocityPermissionsHook;
import dev.neuralnexus.taterlib.velocity.listeners.network.VelocityPluginMessageListener;
import dev.neuralnexus.taterlib.velocity.listeners.player.VelocityPlayerListener;
import dev.neuralnexus.taterlib.velocity.listeners.server.VelocityServerListener;
import dev.neuralnexus.taterlib.velocity.server.VelocityProxyServer;

import org.slf4j.Logger;

import java.time.Duration;
import java.util.stream.Collectors;

@Plugin(
        id = TaterLib.Constants.PROJECT_ID,
        name = TaterLib.Constants.PROJECT_NAME,
        version = TaterLib.Constants.PROJECT_VERSION,
        authors = TaterLib.Constants.PROJECT_AUTHORS,
        description = TaterLib.Constants.PROJECT_DESCRIPTION,
        url = TaterLib.Constants.PROJECT_URL)
public class VelocityTaterLibPlugin implements TaterLibPlugin {
    private static PluginContainer plugin;
    private static ProxyServer proxyServer;

    @Inject
    public VelocityTaterLibPlugin(PluginContainer plugin, ProxyServer server, Logger logger) {
        platformInit(plugin, server, logger);
    }

    /**
     * Gets the proxy server.
     *
     * @return The proxy server.
     */
    public static ProxyServer getProxyServer() {
        return proxyServer;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        platformEnable();
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        platformDisable();
    }

    @Override
    public void platformInit(Object plugin, Object server, Object logger) {
        VelocityTaterLibPlugin.plugin = (PluginContainer) plugin;
        VelocityTaterLibPlugin.proxyServer = (ProxyServer) server;

        TaterAPIProvider.addHook(new VelocityPermissionsHook());
        pluginStart(
                plugin, server, logger, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.VELOCITY);
        api.setPluginList(
                () ->
                        proxyServer.getPluginManager().getPlugins().stream()
                                .map(
                                        p ->
                                                new PluginInfo(
                                                        p.getDescription()
                                                                .getName()
                                                                .orElse("Unknown"),
                                                        p.getDescription()
                                                                .getVersion()
                                                                .orElse("Unknown")))
                                .collect(Collectors.toSet()));
        api.setServer(VelocityProxyServer::instance);
    }

    @Override
    public void platformEnable() {
        // Register listeners
        EventManager eventManager = proxyServer.getEventManager();
        eventManager.register(plugin, new VelocityPlayerListener());
        eventManager.register(plugin, new VelocityPluginMessageListener());
        eventManager.register(plugin, new VelocityServerListener());

        proxyServer
                .getScheduler()
                .buildTask(
                        plugin,
                        () -> {
                            // Register commands
                            CommandEvents.REGISTER_COMMAND.invoke(
                                    new VelocityCommandRegisterEvent());
                            CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                                    new VelocityBrigadierCommandRegisterEvent());

                            // Register plugin messages
                            NetworkEvents.REGISTER_PLUGIN_MESSAGES.invoke(
                                    new VelocityRegisterPluginMessagesEvent());

                            // Fire server started event
                            ServerEvents.STARTED.invoke(new VelocityServerStartedEvent());
                        })
                .delay(Duration.ofSeconds(5))
                .schedule();
    }

    @Override
    public void platformDisable() {
        ServerEvents.STOPPED.invoke(new VelocityServerStoppedEvent());
        pluginStop();
    }
}
