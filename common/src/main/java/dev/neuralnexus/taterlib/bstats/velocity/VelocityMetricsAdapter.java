package dev.neuralnexus.taterlib.bstats.velocity;

import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.proxy.ProxyServer;

import org.slf4j.Logger;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Velocity metrics adapter for BStats to allow for easy multi-platform support. */
public class VelocityMetricsAdapter {
    public static Object setupMetrics(
            Object plugin, Object server, Object pluginLogger, int pluginId) {
        // ------------------------------------------------------------------------------------------
        System.out.println(
                "VelocityMetricsAdapter.setupMetrics: " + (plugin instanceof PluginContainer));
        // ------------------------------------------------------------------------------------------

        Path configDir = Paths.get("plugins");
        try {
            configDir =
                    Paths.get(
                            VelocityMetricsAdapter.class
                                            .getProtectionDomain()
                                            .getCodeSource()
                                            .getLocation()
                                            .toURI()
                                    + File.separator
                                    + "plugins");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // Reflect to get Metrics class, as it's not public and dependency injection is a bit weird
        // from here
        try {
            Class<?> metricsClass = Class.forName("org.bstats.velocity.Metrics");
            Constructor<?> constructor =
                    metricsClass.getDeclaredConstructor(
                            Object.class, ProxyServer.class, Logger.class, Path.class, int.class);
            constructor.setAccessible(true);
            return constructor.newInstance(plugin, server, pluginLogger, configDir, pluginId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
