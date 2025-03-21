/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.metrics.bstats.bukkit;

import org.bstats.bukkit.Metrics;
import org.bstats.charts.CustomChart;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/** Bukkit metrics adapter for BStats to allow for easy multi-platform support. */
public class BukkitMetricsAdapter {
    public static Object setupMetrics(Object plugin, int pluginId, List<CustomChart> charts) {
        if (!(plugin instanceof JavaPlugin)) {
            return null;
        }
        Metrics metrics = new Metrics((JavaPlugin) plugin, pluginId);
        charts.forEach(metrics::addCustomChart);
        return metrics;
    }
}
