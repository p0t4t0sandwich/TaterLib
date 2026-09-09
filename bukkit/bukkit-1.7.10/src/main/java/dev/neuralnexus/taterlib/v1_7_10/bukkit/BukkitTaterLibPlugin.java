/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.bukkit;

import dev.neuralnexus.taterapi.Result;
import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.HumanEntity;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
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
import dev.neuralnexus.taterlib.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.entity.BukkitLivingEntity;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.event.network.BukkitRegisterPacketChannelsEvent;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.server.BukkitServer;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

@AConstraint(
        platform = Platform.BUKKIT,
        version = @Versions(min = MinecraftVersion.V7, max = MinecraftVersion.V7_10))
public class BukkitTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        if (!TaterAPI.hasLoaded() && MetaAPI.instance().platform().isBukkit()) {
            TaterAPI.setLoaded(true);

            TaterAPI.instance().setServer(Platforms.BUKKIT, BukkitServer::instance);
        }
        TaterAPI.WRAPPERS.register(Player.class, input -> Result.success(new BukkitPlayer(input)));
        TaterAPI.WRAPPERS.register(Entity.class, input -> Result.success(new BukkitEntity(input)));
        DataRegistry.register(Identifiable.class, Entity.class)
                .immutable(Keys.UUID, e -> e::getUniqueId);
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
        DataRegistry.register(Nameable.class, Player.class)
                .immutable(Keys.DISPLAY_NAME, p -> p::getDisplayName)
                .immutable(Keys.NAME, p -> p::getName);
        DataRegistry.register(Nameable.class, Entity.class)
                .mutable(
                        Keys.CUSTOM_NAME,
                        _ -> {
                            // TODO: Find a way to query the value
                            throw new VersionFeatureNotSupportedException();
                        },
                        _ -> {
                            // TODO: Find a way to set the value
                            throw new VersionFeatureNotSupportedException();
                        });
        DataRegistry.register(Identifiable.class, CommandSender.class)
                .immutable(
                        Keys.UUID,
                        s -> () -> TaterAPI.uuidFromName(s.getName()).orElse(Notifiable.NIL_UUID));
        DataRegistry.register(Nameable.class, CommandSender.class)
                .immutable(Keys.NAME, s -> s::getName);
        DataRegistry.register(HumanEntity.class, Player.class)
                .mutable(
                        Keys.GAME_MODE,
                        p -> () -> GameMode.fromName(p.getGameMode().name()),
                        p -> v -> p.setGameMode(org.bukkit.GameMode.valueOf(v.name())));
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
