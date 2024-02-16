package dev.neuralnexus.taterlib.bstats;

import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.bstats.bukkit.BukkitMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.bungeecord.BungeeCordMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.sponge.SpongeMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.velocity.VelocityMetricsAdapter;

import java.util.Map;

/** Metrics adapter for BStats to allow for easy multi-platform support. */
public class MetricsAdapter {
    public static Object setupMetrics(
            Object plugin,
            Object pluginServer,
            Object pluginLogger,
            Map<ServerType, Integer> pluginIds) {
        ServerType serverType = ServerType.serverType();
        if (serverType.isBukkitBased()) {
            return BukkitMetricsAdapter.setupMetrics(plugin, pluginIds.get(ServerType.BUKKIT));
        } else if (serverType.isBungeeCordBased()) {
            return BungeeCordMetricsAdapter.setupMetrics(
                    plugin, pluginIds.get(ServerType.BUNGEECORD));
        } else if (serverType.isSpongeBased()) {
            return SpongeMetricsAdapter.setupMetrics(
                    plugin, pluginLogger, pluginIds.get(ServerType.SPONGE));
        } else if (serverType.isVelocityBased()) {
            return VelocityMetricsAdapter.setupMetrics(
                    plugin, pluginServer, pluginLogger, pluginIds.get(ServerType.VELOCITY));
        }
        return null;
    }
}
