/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.metrics.bstats.sponge;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.util.PathUtils;

import org.apache.logging.log4j.Logger;
import org.bstats.charts.CustomChart;
import org.bstats.sponge.Metrics;
import org.spongepowered.plugin.PluginContainer;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.util.List;

/** Sponge metrics adapter for BStats to allow for easy multi-platform support. */
public class SpongeMetricsAdapter {
    public static Object setupMetrics(
            Object plugin, Object pluginLogger, int pluginId, List<CustomChart> charts) {
        // TODO: look into SpongeForge support
        if (Platform.get().is(Platform.SPONGE_FORGE)) {
            return null;
        }

        if (!(plugin instanceof PluginContainer)) {
            return null;
        }
        Path configDir = PathUtils.getConfigFolder();
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
