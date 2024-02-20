package org.bukkit;

import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Logger;

/** Fake Bukkit class. */
public class Bukkit {
    public static Logger getLogger() {
        return null;
    }

    public static Server getServer() {
        return null;
    }

    public static BukkitScheduler getScheduler() {
        return null;
    }

    public static boolean getOnlineMode() {
        return false;
    }

    public static String getVersion() {
        return null;
    }

    public static String getName() {
        return null;
    }

    public static Object[] getOnlinePlayers() {
        return null;
    }
}
