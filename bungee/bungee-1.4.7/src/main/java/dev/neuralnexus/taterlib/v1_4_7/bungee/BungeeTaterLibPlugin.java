/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_4_7.bungee;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterapi.registries.DataRegistry;
import dev.neuralnexus.taterapi.serialization.Result;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.bungee.entity.player.BungeePlayer;
import dev.neuralnexus.taterlib.bungee.event.command.BungeeCommandRegisterEvent;
import dev.neuralnexus.taterlib.bungee.listeners.network.BungeePluginMessageListener;
import dev.neuralnexus.taterlib.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterlib.v1_4_7.bungee.listeners.player.BungeePlayerListener;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

@AConstraint(
        platform = Platform.BUNGEECORD,
        version = @Versions(min = MinecraftVersion.V4_2, max = MinecraftVersion.V7_10))
public class BungeeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPI.setLoaded(true);
        TaterAPI.instance().setServer(Platforms.BUNGEECORD, BungeeProxyServer::instance);
        TaterAPI.WRAPPERS.register(
                ProxiedPlayer.class, input -> Result.success(new BungeePlayer(input)));
        DataRegistry.register(Identifiable.class, ProxiedPlayer.class)
                .immutable(
                        Keys.UUID,
                        _ -> {
                            // TODO: Find a way to get the UUID
                            throw new VersionFeatureNotSupportedException();
                        });
        DataRegistry.register(Nameable.class, ProxiedPlayer.class)
                .immutable(Keys.DISPLAY_NAME, p -> p::getDisplayName)
                .immutable(Keys.NAME, p -> p::getName);
        DataRegistry.register(Nameable.class, CommandSender.class)
                .immutable(Keys.NAME, s -> s::getName);
    }

    @Override
    public void onEnable() {
        TaterLibPlugin.super.onEnable();
        // Register listeners
        Plugin plugin = (Plugin) TaterLib.mod();
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        TaterAPI.scheduler()
                .runLaterAsync(
                        () ->
                                CommandEvents.REGISTER_COMMAND.invoke(
                                        new BungeeCommandRegisterEvent()),
                        5000L);
        pluginManager.registerListener(plugin, new BungeePlayerListener());
        pluginManager.registerListener(plugin, new BungeePluginMessageListener());
        ServerEvents.STARTING.invoke(new ServerStartingEvent() {});
        TaterAPI.scheduler()
                .runLaterAsync(
                        () -> ServerEvents.STARTED.invoke(new ServerStartedEvent() {}), 5000L);
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {});
        ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {});
        TaterLibPlugin.super.onDisable();
    }
}
