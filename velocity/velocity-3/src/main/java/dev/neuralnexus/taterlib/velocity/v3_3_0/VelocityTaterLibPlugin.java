/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0;

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
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.impl.metadata.VelocityData;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.command.VelocityBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.command.VelocityCommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.network.VelocityRegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.server.VelocityServerStartedEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.server.VelocityServerStoppedEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.event.server.VelocityServerStoppingEvent;
import dev.neuralnexus.taterlib.velocity.v3_3_0.hooks.permissions.VelocityPermissionsHook;
import dev.neuralnexus.taterlib.velocity.v3_3_0.listeners.network.VelocityPluginMessageListener;
import dev.neuralnexus.taterlib.velocity.v3_3_0.listeners.player.VelocityPlayerListener;
import dev.neuralnexus.taterlib.velocity.v3_3_0.listeners.server.VelocityServerListener;
import dev.neuralnexus.taterlib.velocity.v3_3_0.server.VelocityProxyServer;

import java.time.Duration;

@Plugin(
        id = TaterLib.Constants.PROJECT_ID,
        name = TaterLib.Constants.PROJECT_NAME,
        version = TaterLib.Constants.PROJECT_VERSION,
        authors = TaterLib.Constants.PROJECT_AUTHORS,
        description = TaterLib.Constants.PROJECT_DESCRIPTION,
        url = TaterLib.Constants.PROJECT_URL)
public class VelocityTaterLibPlugin implements TaterLibPlugin {
    @Inject
    public VelocityTaterLibPlugin(PluginContainer plugin, ProxyServer server) {
        TaterAPIProvider.setPrimaryPlatform(Platform.VELOCITY);
        VelocityData.setProxyServer(server);
        onInit();
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        onEnable();
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        ServerEvents.STOPPING.invoke(new VelocityServerStoppingEvent(event));
        onDisable();
    }

    @Override
    public void onInit() {
        // TODO: Remove when Velocity loader is implemented
        TaterAPIProvider.addHook(new VelocityPermissionsHook());
        start();
        TaterAPIProvider.api(Platform.VELOCITY)
                .ifPresent(api -> api.setServer(VelocityProxyServer::instance));
    }

    @Override
    public void onEnable() {
        // Register listeners
        PluginContainer plugin = (PluginContainer) Loader.instance().plugin();
        ProxyServer proxyServer = (ProxyServer) Loader.instance().server();
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
    public void onDisable() {
        ServerEvents.STOPPED.invoke(new VelocityServerStoppedEvent());
        stop();
    }
}
