package dev.neuralnexus.taterlib.bstats.bukkit;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit metrics adapter for BStats to allow for easy multi-platform support. */
public class BukkitMetricsAdapter {
    public static Object setupMetrics(Object plugin, int pluginId) {
        return new Metrics((JavaPlugin) plugin, pluginId);
    }
}
