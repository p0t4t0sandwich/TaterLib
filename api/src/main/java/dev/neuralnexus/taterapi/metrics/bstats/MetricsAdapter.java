/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.metrics.bstats;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.Side;
import dev.neuralnexus.taterapi.metrics.bstats.bukkit.BukkitBetaMetricsAdapter;
import dev.neuralnexus.taterapi.metrics.bstats.bukkit.BukkitMetricsAdapter;
import dev.neuralnexus.taterapi.metrics.bstats.bungeecord.BungeeCordMetricsAdapter;
import dev.neuralnexus.taterapi.metrics.bstats.sponge.SpongeMetricsAdapter;
import dev.neuralnexus.taterapi.metrics.bstats.velocity.VelocityMetricsAdapter;

import org.bstats.charts.CustomChart;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/** Metrics adapter for BStats to allow for easy multi-platform support. */
public class MetricsAdapter {
    public static @Nullable BStatsMetrics setupMetrics(
            Object plugin,
            Object pluginServer,
            Object pluginLogger,
            Map<Platform, Integer> pluginIds,
            List<CustomChart> charts) {
        MetaAPI api = MetaAPI.instance();
        if (api.side().is(Side.CLIENT)) {
            return null;
        }
        Object metrics = null;
        if (api.isPlatformPresent(Platforms.BUKKIT)) {
            if (api.version().isOlderThan(MinecraftVersions.V0)) {
                metrics =
                        BukkitBetaMetricsAdapter.setupMetrics(
                                plugin, pluginIds.get(Platforms.BUKKIT), Collections.emptyList());
            } else {
                metrics =
                        BukkitMetricsAdapter.setupMetrics(
                                plugin, pluginIds.get(Platforms.BUKKIT), charts);
            }
        } else if (api.isPlatformPresent(Platforms.BUNGEECORD)) {
            metrics =
                    BungeeCordMetricsAdapter.setupMetrics(
                            plugin, pluginIds.get(Platforms.BUNGEECORD), charts);
        } else if (api.isPlatformPresent(Platforms.SPONGE)) {
            metrics =
                    SpongeMetricsAdapter.setupMetrics(
                            plugin, pluginLogger, pluginIds.get(Platforms.SPONGE), charts);
        } else if (api.isPlatformPresent(Platforms.VELOCITY)) {
            metrics =
                    VelocityMetricsAdapter.setupMetrics(
                            plugin,
                            pluginServer,
                            pluginLogger,
                            pluginIds.get(Platforms.VELOCITY),
                            charts);
        }
        return new BStatsMetrics(metrics);
    }

    public static BStatsMetrics setupMetrics(
            Object plugin,
            Object pluginServer,
            Object pluginLogger,
            Map<Platform, Integer> pluginIds) {
        return setupMetrics(plugin, pluginServer, pluginLogger, pluginIds, Collections.emptyList());
    }
}
