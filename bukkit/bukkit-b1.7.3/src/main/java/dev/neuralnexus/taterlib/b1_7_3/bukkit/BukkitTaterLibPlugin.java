/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.b1_7_3.bukkit;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.server.BukkitServerStartedEvent;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.server.BukkitServerStartingEvent;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.server.BukkitServerStoppedEvent;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.server.BukkitServerStoppingEvent;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.hooks.permissions.BukkitPermissionsHook;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.server.BukkitServer;
import dev.neuralnexus.taterloader.Loader;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class BukkitTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPIProvider.addHook(new BukkitPermissionsHook());
        start();
        TaterAPIProvider.api(Platform.BUKKIT)
                .ifPresent(api -> api.setServer(() -> new BukkitServer(Bukkit.getServer())));
    }

    @Override
    public void onEnable() {
        if (TaterAPIProvider.isPrimaryPlatform(Platform.BUKKIT)) {
            // Register listeners
            Plugin plugin = (Plugin) Loader.instance().plugin();
            PluginManager pluginManager = Bukkit.getServer().getPluginManager();
            pluginManager.registerEvent(
                    Event.Type.BLOCK_BREAK,
                    new BukkitBlockListener(),
                    Event.Priority.Normal,
                    plugin);
            Bukkit.getServer()
                    .getScheduler()
                    .scheduleSyncDelayedTask(
                            plugin,
                            () ->
                                    CommandEvents.REGISTER_COMMAND.invoke(
                                            new BukkitCommandRegisterEvent()),
                            200L);
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
            ServerEvents.STARTING.invoke(new BukkitServerStartingEvent());
            Bukkit.getServer()
                    .getScheduler()
                    .scheduleSyncDelayedTask(
                            plugin,
                            () -> ServerEvents.STARTED.invoke(new BukkitServerStartedEvent()),
                            5 * 20L);
        }
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BukkitServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BukkitServerStoppedEvent());
        stop();
    }
}
