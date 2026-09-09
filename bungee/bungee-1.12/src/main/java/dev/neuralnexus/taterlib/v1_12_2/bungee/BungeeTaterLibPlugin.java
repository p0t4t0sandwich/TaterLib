/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.bungee;

import dev.neuralnexus.taterapi.Result;
import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterapi.registries.DataRegistry;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.bungee.entity.player.BungeePlayer;
import dev.neuralnexus.taterlib.bungee.event.command.BungeeCommandRegisterEvent;
import dev.neuralnexus.taterlib.bungee.listeners.network.BungeePluginMessageListener;
import dev.neuralnexus.taterlib.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterlib.v1_12_2.bungee.listeners.player.BungeePlayerListener;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.concurrent.TimeUnit;

@AConstraint(
        platform = Platform.BUNGEECORD,
        version = @Versions(min = MinecraftVersion.V12, max = MinecraftVersion.V15_2))
public class BungeeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPI.setLoaded(true);
        TaterAPI.instance().setServer(Platforms.BUNGEECORD, BungeeProxyServer::instance);
        TaterAPI.WRAPPERS.register(
                ProxiedPlayer.class, input -> Result.success(new BungeePlayer(input)));
        DataRegistry.register(Identifiable.class, ProxiedPlayer.class)
                .immutable(Keys.UUID, p -> p::getUniqueId);
        DataRegistry.register(Nameable.class, ProxiedPlayer.class)
                .immutable(Keys.DISPLAY_NAME, p -> p::getDisplayName)
                .immutable(Keys.NAME, p -> p::getName);
        DataRegistry.register(Identifiable.class, CommandSender.class)
                .immutable(
                        Keys.UUID,
                        s ->
                                () -> {
                                    if (s instanceof ProxiedPlayer p) return p.getUniqueId();
                                    return Notifiable.NIL_UUID;
                                });
        DataRegistry.register(Nameable.class, CommandSender.class)
                .immutable(Keys.NAME, s -> s::getName);
    }

    @Override
    public void onEnable() {
        TaterLibPlugin.super.onEnable();
        // Register listeners
        Plugin plugin = (Plugin) TaterLib.mod();
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
        TaterLibPlugin.super.onDisable();
    }
}
