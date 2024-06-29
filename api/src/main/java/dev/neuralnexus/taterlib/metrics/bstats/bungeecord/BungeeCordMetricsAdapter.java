/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.metrics.bstats.bungeecord;

import net.md_5.bungee.api.plugin.Plugin;

import org.bstats.bungeecord.Metrics;
import org.bstats.charts.CustomChart;

import java.util.List;

/** BungeeCord metrics adapter for BStats to allow for easy multi-platform support. */
public class BungeeCordMetricsAdapter {
    public static Object setupMetrics(Object plugin, int pluginId, List<CustomChart> charts) {
        Metrics metrics = new Metrics((Plugin) plugin, pluginId);
        charts.forEach(metrics::addCustomChart);
        return metrics;
    }
}
