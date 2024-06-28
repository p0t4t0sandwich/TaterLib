/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.bukkit;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.logger.impl.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.network.BukkitRegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.server.BukkitServerStartedEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.server.BukkitServerStartingEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.server.BukkitServerStoppedEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.server.BukkitServerStoppingEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.hooks.permissions.BukkitPermissionsHook;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.server.BukkitServer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitTaterLibPlugin implements TaterLibPlugin {
    public static JavaPlugin plugin;

    @Override
    public void onInit(Object plugin, Object server, Object logger) {
        BukkitTaterLibPlugin.plugin = (JavaPlugin) plugin;
        TaterAPIProvider.addHook(new BukkitPermissionsHook());
        start(plugin, server, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPIProvider.api(Platform.BUKKIT)
                .ifPresent(api -> api.setServer(() -> new BukkitServer(Bukkit.getServer())));
    }

    @Override
    public void onEnable() {
        if (TaterAPIProvider.isPrimaryPlatform(Platform.BUKKIT)) {
            // Register listeners
            PluginManager pluginManager = Bukkit.getServer().getPluginManager();
            pluginManager.registerEvents(new BukkitBlockListener(), plugin);
            pluginManager.registerEvents(new BukkitEntityListener(), plugin);
            pluginManager.registerEvents(new BukkitPlayerListener(), plugin);
            ServerEvents.STARTING.invoke(new BukkitServerStartingEvent());
            Bukkit.getServer()
                    .getScheduler()
                    .scheduleSyncDelayedTask(
                            plugin,
                            () -> ServerEvents.STARTED.invoke(new BukkitServerStartedEvent()),
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
                                NetworkEvents.REGISTER_PLUGIN_MESSAGES.invoke(
                                        new BukkitRegisterPluginMessagesEvent());
                            },
                            200L);
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
