/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.hooks.metrics;

import dev.neuralnexus.taterapi.hooks.Hook;
import dev.neuralnexus.taterapi.server.metrics.TPSProvider;

import me.lucko.spark.api.Spark;
import me.lucko.spark.api.SparkProvider;
import me.lucko.spark.api.statistic.StatisticWindow;
import me.lucko.spark.api.statistic.types.DoubleStatistic;

public class SparkHook implements Hook, TPSProvider {
    private static SparkHook instance;
    private final Spark spark;

    public SparkHook() {
        instance = this;
        this.spark = SparkProvider.get();
    }

    public static SparkHook get() {
        return instance;
    }

    @Override
    public String name() {
        return null;
    }

    public DoubleStatistic<StatisticWindow.TicksPerSecond> getTPS() {
        return this.spark.tps();
    }

    @Override
    public double tpsLast5Secs() {
        return this.getTPS().poll(StatisticWindow.TicksPerSecond.SECONDS_5);
    }

    @Override
    public double tpsLast1Min() {
        return this.getTPS().poll(StatisticWindow.TicksPerSecond.MINUTES_1);
    }

    @Override
    public double tpsLast5Min() {
        return this.getTPS().poll(StatisticWindow.TicksPerSecond.MINUTES_5);
    }

    @Override
    public double tpsLast15Min() {
        return this.getTPS().poll(StatisticWindow.TicksPerSecond.MINUTES_15);
    }
}
