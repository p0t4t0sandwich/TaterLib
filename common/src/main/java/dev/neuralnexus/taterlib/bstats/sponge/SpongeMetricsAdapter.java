package dev.neuralnexus.taterlib.bstats.sponge;

import dev.neuralnexus.taterlib.api.info.ServerType;

import org.apache.logging.log4j.Logger;
import org.spongepowered.plugin.PluginContainer;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Sponge metrics adapter for BStats to allow for easy multi-platform support. */
public class SpongeMetricsAdapter {
    public static Object setupMetrics(Object plugin, Object pluginLogger, int pluginId) {
        Path configDir = Paths.get(ServerType.SPONGE.dataFolders().configFolder());
        // Reflect to get Metrics class, as it's not public and dependency injection is a bit weird
        // from here
        try {
            Class<?> metricsClass = Class.forName("org.bstats.sponge.Metrics");
            Constructor<?> constructor =
                    metricsClass.getDeclaredConstructor(
                            PluginContainer.class, Logger.class, Path.class, int.class);
            constructor.setAccessible(true);
            return constructor.newInstance(plugin, pluginLogger, configDir, pluginId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
