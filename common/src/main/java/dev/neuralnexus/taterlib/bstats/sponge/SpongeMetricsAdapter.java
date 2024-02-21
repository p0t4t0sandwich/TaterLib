package dev.neuralnexus.taterlib.bstats.sponge;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;

import org.apache.logging.log4j.Logger;
import org.bstats.charts.CustomChart;
import org.bstats.sponge.Metrics;
import org.spongepowered.plugin.PluginContainer;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

/** Sponge metrics adapter for BStats to allow for easy multi-platform support. */
public class SpongeMetricsAdapter {
    public static Object setupMetrics(
            Object plugin, Object pluginLogger, int pluginId, Set<CustomChart> charts) {
        // TODO: look into SpongeForge support
        if (TaterAPIProvider.serverType().is(ServerType.SPONGE_FORGE)) {
            return null;
        }

        if (!(plugin instanceof PluginContainer)) {
            return null;
        }
        Path configDir = Paths.get(ServerType.SPONGE.dataFolders().configFolder());
        if (configDir.toFile().exists()) {
            configDir = configDir.toAbsolutePath();
        }
        // Reflect to get Metrics class, as it's not public and dependency injection is a bit weird
        // from here
        try {
            Class<?> metricsClass = Class.forName("org.bstats.sponge.Metrics");
            Constructor<?> constructor =
                    metricsClass.getDeclaredConstructor(
                            PluginContainer.class, Logger.class, Path.class, int.class);
            constructor.setAccessible(true);
            Metrics metrics =
                    (Metrics) constructor.newInstance(plugin, pluginLogger, configDir, pluginId);
            charts.forEach(metrics::addCustomChart);
            return metrics;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
