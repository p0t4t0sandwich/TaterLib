package dev.neuralnexus.taterlib.bstats.sponge;

import org.apache.logging.log4j.Logger;
import org.spongepowered.plugin.PluginContainer;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Sponge metrics adapter for BStats to allow for easy multi-platform support. */
public class SpongeMetricsAdapter {
    public static Object setupMetrics(Object plugin, Object pluginLogger, int pluginId) {
        Path configDir = Paths.get("config");
        try {
            configDir =
                    Paths.get(
                            SpongeMetricsAdapter.class
                                            .getProtectionDomain()
                                            .getCodeSource()
                                            .getLocation()
                                            .toURI()
                                    + File.separator
                                    + "config");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // Reflect to get Metrics class, as it's not public and dependency injection is a bit weird
        // from here
        try {
            Class<?> metricsClass = Class.forName("org.bstats.sponge.Metrics");
            return metricsClass
                    .getDeclaredConstructor(
                            PluginContainer.class, Logger.class, Path.class, int.class)
                    .newInstance(plugin, pluginLogger, configDir, pluginId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
