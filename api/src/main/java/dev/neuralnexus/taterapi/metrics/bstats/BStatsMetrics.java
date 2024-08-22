package dev.neuralnexus.taterapi.metrics.bstats;

/**
 * Dummy interface to handle relocated BStats objects easier
 */
public interface BStatsMetrics {
    /**
     * Stop the thread associated with this metrics instance
     */
    void shutdown();
}
