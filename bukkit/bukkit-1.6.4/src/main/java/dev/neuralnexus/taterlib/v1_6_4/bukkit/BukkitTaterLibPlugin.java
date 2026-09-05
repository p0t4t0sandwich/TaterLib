/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.bukkit;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterapi.registries.DataRegistry;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.entity.BukkitLivingEntity;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.network.BukkitRegisterPacketChannelsEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.server.BukkitServer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

@AConstraint(
        platform = Platform.BUKKIT,
        version = @Versions(min = MinecraftVersion.V6, max = MinecraftVersion.V6_4))
public class BukkitTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        if (!TaterAPI.hasLoaded() && MetaAPI.instance().platform().isBukkit()) {
            TaterAPI.setLoaded(true);

            TaterAPI.instance().setServer(Platforms.BUKKIT, BukkitServer::instance);
        }
        WrapperRegistry.register(Player.class, BukkitPlayer::new);
        WrapperRegistry.register(Entity.class, BukkitEntity::new);
        DataRegistry.register(Damageable.class, org.bukkit.entity.LivingEntity.class)
                .mutable(
                        Keys.ABSORPTION,
                        e -> () -> BukkitLivingEntity.helper$absorption(e),
                        e -> v -> BukkitLivingEntity.helper$absorption(e, v))
                .mutable(
                        Keys.HEALTH,
                        e -> () -> BukkitLivingEntity.helper$health(e),
                        e -> e::setHealth)
                .mutable(
                        Keys.MAX_HEALTH,
                        e -> () -> BukkitLivingEntity.helper$maxHealth(e),
                        e -> e::setMaxHealth);
    }

    @Override
    public void onEnable() {
        TaterLibPlugin.super.onEnable();
        if (MetaAPI.instance().platform().isBukkit()) {
            // Register listeners
            Plugin plugin = (Plugin) TaterLib.mod();
            PluginManager pluginManager = Bukkit.getServer().getPluginManager();
            pluginManager.registerEvents(new BukkitBlockListener(), plugin);
            pluginManager.registerEvents(new BukkitEntityListener(), plugin);
            pluginManager.registerEvents(new BukkitPlayerListener(), plugin);
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
                            () -> {
                                // Register commands
                                CommandEvents.REGISTER_COMMAND.invoke(
                                        new BukkitCommandRegisterEvent());

                                // Register plugin messages
                                NetworkEvents.REGISTER_CHANNELS.invoke(
                                        new BukkitRegisterPacketChannelsEvent());
                            },
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
