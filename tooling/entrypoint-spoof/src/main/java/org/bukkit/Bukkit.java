/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package org.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

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

    public static String getVersion() {
        return "";
    }

    public static String getName() {
        return "";
    }

    public static Player[] getOnlinePlayers() {
        return server.getOnlinePlayers();
    }
}
