/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.b1_7_3.bukkit;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterapi.registries.DataRegistry;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.bukkit.event.command.BukkitCommandRegisterEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

@AConstraint(platform = Platform.BUKKIT, version = @Versions(MinecraftVersion.B1_7_3))
public class BukkitTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        if (!TaterAPI.hasLoaded() && MetaAPI.instance().platform().isBukkit()) {
            TaterAPI.setLoaded(true);

            TaterAPI.instance().setServer(Platforms.BUKKIT, BukkitServer::instance);
        }
        WrapperRegistry.register(Player.class, BukkitPlayer::new);
        WrapperRegistry.register(Entity.class, BukkitEntity::new);
        DataRegistry.register(Damageable.class, LivingEntity.class)
                .mutable(
                        Keys.HEALTH,
                        e -> () -> (double) e.getHealth(),
                        e -> v -> e.setHealth((int) v.doubleValue()))
                .mutable(
                        Keys.MAX_HEALTH,
                        _ -> () -> 200.0d, // Value is hard-coded as 200??,
                        _ ->
                                _ -> {
                                    throw new VersionFeatureNotSupportedException();
                                });
    }

    @Override
    public void onEnable() {
        TaterLibPlugin.super.onEnable();
        if (MetaAPI.instance().platform().isBukkit()) {
            // Register listeners
            Plugin plugin = (Plugin) TaterLib.mod();
            PluginManager pluginManager = Bukkit.getServer().getPluginManager();
            pluginManager.registerEvent(
                    Event.Type.BLOCK_BREAK,
                    new BukkitBlockListener(),
                    Event.Priority.Normal,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.ENTITY_DAMAGE,
                    new BukkitEntityListener(),
                    Event.Priority.Normal,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.ENTITY_DEATH,
                    new BukkitEntityListener(),
                    Event.Priority.Normal,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.CREATURE_SPAWN,
                    new BukkitEntityListener(),
                    Event.Priority.Normal,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.PLAYER_JOIN,
                    new BukkitPlayerListener(),
                    Event.Priority.Normal,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.PLAYER_QUIT,
                    new BukkitPlayerListener(),
                    Event.Priority.Normal,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.PLAYER_CHAT,
                    new BukkitPlayerListener(),
                    Event.Priority.Highest,
                    plugin);
            pluginManager.registerEvent(
                    Event.Type.PLAYER_RESPAWN,
                    new BukkitPlayerListener(),
                    Event.Priority.Normal,
                    plugin);
            ServerEvents.STARTING.invoke(new ServerStartingEvent() {});
            Bukkit.getServer()
                    .getScheduler()
                    .scheduleSyncDelayedTask(
                            plugin,
                            () -> ServerEvents.STARTED.invoke(new ServerStartedEvent() {}),
                            5 * 20L);
            Bukkit.getServer()
                    .getScheduler()
                    .scheduleSyncDelayedTask(
                            plugin,
                            () ->
                                    CommandEvents.REGISTER_COMMAND.invoke(
                                            new BukkitCommandRegisterEvent()),
                            10 * 20L);
        }
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {});
        ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {});
        TaterLibPlugin.super.onDisable();
    }
}
