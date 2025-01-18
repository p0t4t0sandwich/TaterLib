/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.bukkit;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.command.BukkitCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.event.network.BukkitRegisterPacketChannelsEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.listeners.block.BukkitBlockListener;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.listeners.entity.BukkitEntityListener;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.server.BukkitServer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

@SuppressWarnings("unused")
public class BukkitTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPIProvider.api(Platforms.BUKKIT)
                .ifPresent(api -> api.setServer(BukkitServer::instance));
    }

    @Override
    public void onEnable() {
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.BUKKIT)) {
            // Register listeners
            Plugin plugin = (Plugin) Loader.instance().plugin();
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
                            200L);
        }
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {});
        ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {});
        this.onDisable();
    }
}
