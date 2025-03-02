/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.metrics.bstats;

import dev.neuralnexus.taterapi.metrics.bstats.bukkit.BukkitBetaMetricsAdapter;

/** Dummy class to handle relocated BStats objects easier */
public class BStatsMetrics {
    private final Object metrics;

    public BStatsMetrics(Object metrics) {
        this.metrics = metrics;
    }

    /** Stop the thread associated with this metrics instance */
    public void shutdown() {
        if (metrics instanceof org.bstats.bukkit.Metrics) {
            ((org.bstats.bukkit.Metrics) metrics).shutdown();
        } else if (metrics instanceof org.bstats.bungeecord.Metrics) {
            ((org.bstats.bungeecord.Metrics) metrics).shutdown();
        } else if (metrics instanceof org.bstats.velocity.Metrics) {
            ((org.bstats.velocity.Metrics) metrics).shutdown();
        } else if (metrics instanceof org.bstats.sponge.Metrics) {
            ((org.bstats.sponge.Metrics) metrics).shutdown();
        } else if (metrics instanceof BukkitBetaMetricsAdapter.Metrics) {
            ((org.bstats.velocity.Metrics) metrics).shutdown();
        }
    }
}
