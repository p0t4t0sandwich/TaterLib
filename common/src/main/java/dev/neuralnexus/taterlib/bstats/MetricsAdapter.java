package dev.neuralnexus.taterlib.bstats;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.bstats.bukkit.BukkitBetaMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.bukkit.BukkitMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.bungeecord.BungeeCordMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.sponge.SpongeMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.velocity.VelocityMetricsAdapter;

import org.bstats.charts.CustomChart;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/** Metrics adapter for BStats to allow for easy multi-platform support. */
public class MetricsAdapter {
    public static Object setupMetrics(
            Object plugin,
            Object pluginServer,
            Object pluginLogger,
            Map<Platform, Integer> pluginIds,
            List<CustomChart> charts) {
        Platform platform = Platform.get();
        if (platform.isBukkitBased()) {
            if (TaterAPIProvider.minecraftVersion().isOlderThan(MinecraftVersion.V1_0)) {
                return BukkitBetaMetricsAdapter.setupMetrics(
                        plugin, pluginIds.get(Platform.BUKKIT), Collections.emptyList());
            }
            return BukkitMetricsAdapter.setupMetrics(
                    plugin, pluginIds.get(Platform.BUKKIT), charts);
        } else if (platform.isBungeeCordBased()) {
            return BungeeCordMetricsAdapter.setupMetrics(
                    plugin, pluginIds.get(Platform.BUNGEECORD), charts);
        } else if (platform.isSpongeBased()) {
            return SpongeMetricsAdapter.setupMetrics(
                    plugin, pluginLogger, pluginIds.get(Platform.SPONGE), charts);
        } else if (platform.isVelocityBased()) {
            return VelocityMetricsAdapter.setupMetrics(
                    plugin, pluginServer, pluginLogger, pluginIds.get(Platform.VELOCITY), charts);
        }
        return null;
    }

    public static Object setupMetrics(
            Object plugin,
            Object pluginServer,
            Object pluginLogger,
            Map<Platform, Integer> pluginIds) {
        return setupMetrics(plugin, pluginServer, pluginLogger, pluginIds, Collections.emptyList());
    }
}
