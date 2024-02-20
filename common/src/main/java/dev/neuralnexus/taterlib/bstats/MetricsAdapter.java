package dev.neuralnexus.taterlib.bstats;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.bstats.bukkit.BukkitMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.bukkit.BukkitPoseidonMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.bungeecord.BungeeCordMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.sponge.SpongeMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.velocity.VelocityMetricsAdapter;

import org.bstats.charts.CustomChart;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/** Metrics adapter for BStats to allow for easy multi-platform support. */
public class MetricsAdapter {
    public static Object setupMetrics(
            Object plugin,
            Object pluginServer,
            Object pluginLogger,
            Map<ServerType, Integer> pluginIds,
            Set<CustomChart> charts) {
        ServerType serverType = ServerType.serverType();
        if (serverType.isBukkitBased()) {
            if (serverType.is(ServerType.POSEIDON)) {
                return BukkitPoseidonMetricsAdapter.setupMetrics(
                        plugin, pluginIds.get(ServerType.BUKKIT), charts);
            }
            // Return early if there's no implementation available.
            if (TaterAPIProvider.minecraftVersion().isOlderThan(MinecraftVersion.V1_0)) {
                return null;
            }
            return BukkitMetricsAdapter.setupMetrics(
                    plugin, pluginIds.get(ServerType.BUKKIT), charts);
        } else if (serverType.isBungeeCordBased()) {
            return BungeeCordMetricsAdapter.setupMetrics(
                    plugin, pluginIds.get(ServerType.BUNGEECORD), charts);
        } else if (serverType.isSpongeBased()) {
            return SpongeMetricsAdapter.setupMetrics(
                    plugin, pluginLogger, pluginIds.get(ServerType.SPONGE), charts);
        } else if (serverType.isVelocityBased()) {
            return VelocityMetricsAdapter.setupMetrics(
                    plugin, pluginServer, pluginLogger, pluginIds.get(ServerType.VELOCITY), charts);
        }
        return null;
    }

    public static Object setupMetrics(
            Object plugin,
            Object pluginServer,
            Object pluginLogger,
            Map<ServerType, Integer> pluginIds) {
        return setupMetrics(plugin, pluginServer, pluginLogger, pluginIds, Collections.emptySet());
    }
}
