package dev.neuralnexus.taterlib.bstats;

import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.bstats.bukkit.BukkitMetricsAdapter;
import dev.neuralnexus.taterlib.bstats.bungeecord.BungeeCordMetricsAdapter;

import java.util.Map;

/** Metrics adapter for BStats to allow for easy multi-platform support. */
public class MetricsAdapter {
    public static Object setupMetrics(Object plugin, Map<ServerType, Integer> pluginIds) {
        ServerType serverType = ServerType.serverType();
        if (serverType.isBukkitBased()) {
            return BukkitMetricsAdapter.setupMetrics(plugin, pluginIds.get(ServerType.BUKKIT));
        } else if (serverType.isBungeeCordBased()) {
            return BungeeCordMetricsAdapter.setupMetrics(
                    plugin, pluginIds.get(ServerType.BUNGEECORD));
            //        } else if (serverType.isSpongeBased()) {
            //            SpongeMetricsAdapter.setupMetrics(plugin,
            // pluginIds.get(ServerType.SPONGE));
            //        } else if (serverType.isVelocityBased()) {
            //            VelocityMetricsAdapter.setupMetrics(plugin,
            // pluginIds.get(ServerType.BUNGEECORD));
        }
        return null;
    }
}
