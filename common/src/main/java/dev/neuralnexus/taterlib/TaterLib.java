/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.config.TaterLibConfigLoader;
import dev.neuralnexus.taterlib.depdownloader.DepClassLoader;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.logger.Logger;
import dev.neuralnexus.taterlib.metrics.bstats.TaterLibMetrics;
import dev.neuralnexus.taterlib.modules.bungeecord.BungeeCordModule;
import dev.neuralnexus.taterlib.modules.core.CoreModule;

import java.util.HashMap;
import java.util.Map;

/** Main class for the plugin. */
public class TaterLib {
    private static final TaterLib instance = new TaterLib();
    private static boolean STARTED = false;
    private static boolean RELOADED = false;
    private static final Logger logger = Loader.instance().logger(Constants.PROJECT_ID);

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
            DepClassLoader depClassLoader = new DepClassLoader(TaterLib.class.getClassLoader(), repos, deps);
            depClassLoader.downloadAll();
            depClassLoader.addDepsToClasspath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set up bStats
        TaterLibMetrics.initialize();

        // Config
        TaterLibConfigLoader.load();

        if (STARTED) {
            logger().info(Constants.PROJECT_NAME + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register modules
            Loader.instance().registerPluginModule("taterlib", new CoreModule());
            Loader.instance().registerPluginModule("taterlib", new BungeeCordModule());
        }
        logger().info(Constants.PROJECT_NAME + " has been started!");
    }

    /** Stop */
    public static void stop() {
        if (!STARTED) {
            logger().info(Constants.PROJECT_NAME + " has already stopped!");
            return;
        }
        STARTED = false;

        // Remove references to objects
        TaterLibConfigLoader.unload();

        logger().info(Constants.PROJECT_NAME + " has been stopped!");
        TaterAPIProvider.unregister();
    }

    /** Reload */
    public static void reload() {
        if (!STARTED) {
            logger().info(Constants.PROJECT_NAME + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Start
        start();

        logger().info(Constants.PROJECT_NAME + " has been reloaded!");
    }

    /** Constants used throughout the plugin. */
    public static class Constants {
        public static final String PROJECT_NAME = "TaterLib";
        public static final String PROJECT_ID = "taterlib";
        public static final String PROJECT_VERSION = "1.2.0-SNAPSHOT";
        public static final String PROJECT_AUTHORS = "p0t4t0sandwich";
        public static final String PROJECT_DESCRIPTION =
                "A cross API code library that allows developers to write code that works across multiple modding platforms, and across a wide range of Minecraft versions, all with one JAR file. If TaterLib runs on it, so can your plugin/mod.";
        public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterLib";
    }
}
