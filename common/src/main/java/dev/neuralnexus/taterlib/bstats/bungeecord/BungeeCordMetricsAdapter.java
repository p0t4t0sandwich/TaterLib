package dev.neuralnexus.taterlib.bstats.bungeecord;

import net.md_5.bungee.api.plugin.Plugin;

import org.bstats.bungeecord.Metrics;
import org.bstats.charts.CustomChart;

import java.util.Set;

/** BungeeCord metrics adapter for BStats to allow for easy multi-platform support. */
public class BungeeCordMetricsAdapter {
    public static Object setupMetrics(Object plugin, int pluginId, Set<CustomChart> charts) {
        Metrics metrics = new Metrics((Plugin) plugin, pluginId);
        charts.forEach(metrics::addCustomChart);
        return metrics;
    }
}
