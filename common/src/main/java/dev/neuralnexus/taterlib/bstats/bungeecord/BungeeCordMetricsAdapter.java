package dev.neuralnexus.taterlib.bstats.bungeecord;

import net.md_5.bungee.api.plugin.Plugin;

import org.bstats.bungeecord.Metrics;

/** Bukkit metrics adapter for BStats to allow for easy multi-platform support. */
public class BungeeCordMetricsAdapter {
    public static Object setupMetrics(Object plugin, int pluginId) {
        return new Metrics((Plugin) plugin, pluginId);
    }
}
