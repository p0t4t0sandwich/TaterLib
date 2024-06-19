package org.bukkit;

import org.bukkit.entity.Player;
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
