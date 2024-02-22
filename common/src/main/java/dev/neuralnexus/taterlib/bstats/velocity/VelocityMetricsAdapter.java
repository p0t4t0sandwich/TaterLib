package dev.neuralnexus.taterlib.bstats.velocity;

import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterlib.api.info.ServerType;

import org.bstats.charts.CustomChart;
import org.bstats.velocity.Metrics;
import org.slf4j.Logger;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/** Velocity metrics adapter for BStats to allow for easy multi-platform support. */
public class VelocityMetricsAdapter {
    public static Object setupMetrics(
            Object plugin,
            Object server,
            Object pluginLogger,
            int pluginId,
            List<CustomChart> charts) {
        Path configDir = Paths.get(ServerType.VELOCITY.dataFolders().configFolder());
        if (configDir.toFile().exists()) {
            configDir = configDir.toAbsolutePath();
        }

        // Reflect to get Metrics class, as it's not public and dependency injection is a bit weird
        // from here
        try {
            Class<?> metricsClass = Class.forName("org.bstats.velocity.Metrics");
            Constructor<?> constructor =
                    metricsClass.getDeclaredConstructor(
                            Object.class, ProxyServer.class, Logger.class, Path.class, int.class);
            constructor.setAccessible(true);
            Metrics metrics =
                    (Metrics)
                            constructor.newInstance(
                                    plugin, server, pluginLogger, configDir, pluginId);
            charts.forEach(metrics::addCustomChart);
            return metrics;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
