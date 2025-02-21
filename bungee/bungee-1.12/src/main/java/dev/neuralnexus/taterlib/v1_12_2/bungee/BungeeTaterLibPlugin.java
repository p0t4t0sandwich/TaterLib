/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.bungee;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.bungee.entity.player.BungeePlayer;
import dev.neuralnexus.taterlib.bungee.event.command.BungeeCommandRegisterEvent;
import dev.neuralnexus.taterlib.bungee.listeners.network.BungeePluginMessageListener;
import dev.neuralnexus.taterlib.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterlib.v1_12_2.bungee.listeners.player.BungeePlayerListener;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.concurrent.TimeUnit;

public class BungeeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPIProvider.api(Platforms.BUNGEECORD)
                .ifPresent(api -> api.setServer(BungeeProxyServer::instance));
        WrapperRegistry.register(ProxiedPlayer.class, BungeePlayer::new);
    }

    @Override
    public void onEnable() {
        TaterLib.start();
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
        ServerEvents.STARTING.invoke(new ServerStartingEvent() {});
        ProxyServer.getInstance()
                .getScheduler()
                .schedule(
                        plugin,
                        () -> ServerEvents.STARTED.invoke(new ServerStartedEvent() {}),
                        5L,
                        TimeUnit.SECONDS);
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {});
        ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {});
        TaterLib.stop();
    }
}
