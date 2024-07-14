/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterlib.config.TaterLibConfig;
import dev.neuralnexus.taterlib.config.TaterLibConfigLoader;
import dev.neuralnexus.taterlib.metrics.bstats.TaterLibMetrics;
import dev.neuralnexus.taterlib.modules.bungeecord.BungeeCordModule;
import dev.neuralnexus.taterlib.modules.mclogs.MCLogsModule;
import dev.neuralnexus.taterloader.Loader;
import dev.neuralnexus.taterloader.depdownloader.DepClassLoader;
import dev.neuralnexus.taterloader.impl.LoaderImpl;

import java.util.HashMap;
import java.util.Map;

/** Main class for the plugin. */
public class TaterLib {
    private static final TaterLib instance = new TaterLib();
    private static final Logger logger = Logger.create(LoaderImpl.PROJECT_ID);
    private static boolean RELOADED = false;

    /**
     * Get if the plugin has reloaded
     *
     * @return If the plugin has reloaded
     */
    public static boolean hasReloaded() {
        return RELOADED;
    }

    /**
     * Getter for the singleton instance of the class.
     *
     * @return The singleton instance
     */
    public static TaterLib instance() {
        return instance;
    }

    /**
     * Get the logger
     *
     * @return The logger
     */
    public static Logger logger() {
        return logger;
    }

    /** Start */
    public static void start() {
        try {
            String[] repos = new String[] {"https://maven.neuralnexus.dev/mirror"};
            Map<String, String> deps = new HashMap<>();
            deps.put("gs.mclo:api:3.0.1", "a0f52bb4002f4fe958e9c7af8d2e34fb");
            DepClassLoader depClassLoader =
                    new DepClassLoader(TaterLib.class.getClassLoader(), repos, deps);
            depClassLoader.downloadAll();
            depClassLoader.addDepsToClasspath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TaterLibMetrics.initialize();
        TaterLibConfigLoader.load();

        if (!RELOADED) {
            TaterLibConfig config = TaterLibConfigLoader.config();
            Loader loader = Loader.instance();
            if (config.checkModule("BungeeCord")) {
                loader.registerPluginModule("taterlib", new BungeeCordModule());
            }
            if (config.checkModule("MCLogs")) {
                loader.registerPluginModule("taterlib", new MCLogsModule());
            }
        }
        logger().info(LoaderImpl.PROJECT_NAME + " has been started!");
    }

    /** Stop */
    public static void stop() {
        TaterLibConfigLoader.unload();
        logger().info(LoaderImpl.PROJECT_NAME + " has been stopped!");
        TaterAPIProvider.unregister();
    }

    /** Reload */
    public static void reload() {
        RELOADED = true;
        stop();
        start();
        logger().info(LoaderImpl.PROJECT_NAME + " has been reloaded!");
    }
}
