/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package org.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

/** Fake Bukkit class. */
public class Bukkit {
    static Logger logger;
    static Server server;
    static BukkitScheduler scheduler;

    public static Logger getLogger() {
        return logger;
    }

    public static Server getServer() {
        return server;
    }

    public static PluginManager getPluginManager() {
        return server.getPluginManager();
    }

    public static BukkitScheduler getScheduler() {
        return scheduler;
    }

    public static boolean getOnlineMode() {
        return false;
    }

    /**
     * Gets the version string of this server implementation.
     *
     * @return version of this server implementation
     */
    @NotNull public static String getVersion() {
        return server.getVersion();
    }

    /**
     * Gets the Bukkit version that this server is running.
     *
     * @return version of Bukkit
     */
    @NotNull public static String getBukkitVersion() {
        return server.getBukkitVersion();
    }

    // Paper start - expose game version
    // Entrypoint-Spoof Note: Since Paper ~1.15
    /**
     * Gets the version of game this server implements
     *
     * @return version of game
     */
    @NotNull public static String getMinecraftVersion() {
        return server.getMinecraftVersion();
    }

    // Paper end

    public static Player[] getOnlinePlayers() {
        return server.getOnlinePlayers();
    }
}
