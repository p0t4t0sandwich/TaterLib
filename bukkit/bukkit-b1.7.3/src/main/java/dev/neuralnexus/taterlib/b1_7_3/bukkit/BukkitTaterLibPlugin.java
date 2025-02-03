/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.b1_7_3.bukkit;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.bukkit.event.command.BukkitCommandRegisterEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

@SuppressWarnings("unused")
public class BukkitTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPIProvider.api(Platforms.BUKKIT)
                .ifPresent(api -> api.setServer(BukkitServer::instance));
        WrapperRegistry.register(Player.class, BukkitPlayer::new);
    }

    @Override
    public void onEnable() {
        TaterLib.start();
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.BUKKIT)) {
            // Register listeners
            Plugin plugin = (Plugin) Loader.instance().plugin();
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
        TaterLib.stop();
    }
}
