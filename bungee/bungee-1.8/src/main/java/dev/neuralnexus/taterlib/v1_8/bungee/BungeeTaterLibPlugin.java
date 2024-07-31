/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8.bungee;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.impl.ServerStartedEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStartingEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStoppedEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStoppingEventImpl;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_8.bungee.event.command.BungeeCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_8.bungee.hooks.permissions.BungeePermissionsHook;
import dev.neuralnexus.taterlib.v1_8.bungee.listeners.network.BungeePluginMessageListener;
import dev.neuralnexus.taterlib.v1_8.bungee.listeners.player.BungeePlayerListener;
import dev.neuralnexus.taterlib.v1_8.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterloader.Loader;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.concurrent.TimeUnit;

public class BungeeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPIProvider.addHook(new BungeePermissionsHook());
        start();
        TaterAPIProvider.api(Platform.BUNGEECORD)
                .ifPresent(api -> api.setServer(BungeeProxyServer::instance));
    }

    @Override
    public void onEnable() {
        // Register listeners
        Plugin plugin = (Plugin) Loader.instance().plugin();
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        ProxyServer.getInstance()
                .getScheduler()
                .schedule(
                        plugin,
                        () ->
                                CommandEvents.REGISTER_COMMAND.invoke(
                                        new BungeeCommandRegisterEvent()),
                        5L,
                        TimeUnit.SECONDS);
        pluginManager.registerListener(plugin, new BungeePlayerListener());
        pluginManager.registerListener(plugin, new BungeePluginMessageListener());
        ServerEvents.STARTING.invoke(new ServerStartingEventImpl());
        ProxyServer.getInstance()
                .getScheduler()
                .schedule(
                        plugin,
                        () -> ServerEvents.STARTED.invoke(new ServerStartedEventImpl()),
                        5L,
                        TimeUnit.SECONDS);
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new ServerStoppingEventImpl());
        ServerEvents.STOPPED.invoke(new ServerStoppedEventImpl());
        stop();
    }
}
