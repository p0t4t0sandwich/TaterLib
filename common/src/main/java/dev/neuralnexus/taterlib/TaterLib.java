package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.bstats.custom.TaterLibMetrics;
import dev.neuralnexus.taterlib.config.TaterLibConfigLoader;
import dev.neuralnexus.taterlib.logger.Logger;
import dev.neuralnexus.taterlib.modules.bungeecord.BungeeCordModule;
import dev.neuralnexus.taterlib.modules.core.CoreModule;
import dev.neuralnexus.taterlib.plugin.ModuleLoader;

/** Main class for the plugin. */
public class TaterLib {
    private static final TaterLib instance = new TaterLib();
    private static boolean STARTED = false;
    private static boolean RELOADED = false;
    private static ModuleLoader moduleLoader;
    private Object plugin;
    private Object server;
    private Logger logger;

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
     * Get the plugin
     *
     * @return The plugin
     */
    public static Object plugin() {
        return instance.plugin;
    }

    /**
     * Set the plugin
     *
     * @param plugin The plugin
     */
    private static void setPlugin(Object plugin) {
        instance.plugin = plugin;
    }

    /**
     * Set the plugin server
     *
     * @param server The plugin server
     */
    private static void setPluginServer(Object server) {
        instance.server = server;
    }

    /**
     * Get the logger
     *
     * @return The logger
     */
    public static Logger logger() {
        return instance.logger;
    }

    /**
     * Set the logger
     *
     * @param logger The logger
     */
    private static void setLogger(Logger logger) {
        instance.logger = logger;
    }

    /**
     * Start
     *
     * @param plugin The plugin
     * @param server The plugin server
     * @param logger The logger
     */
    public static void start(Object plugin, Object server, Logger logger) {
        if (server != null) {
            setPluginServer(server);
        }
        setPlugin(plugin);
        setLogger(logger);

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
            moduleLoader = new TaterLibModuleLoader();
            moduleLoader.registerModule(new CoreModule());
            moduleLoader.registerModule(new BungeeCordModule());
        }

        // Start modules
        logger().info("Starting modules: " + moduleLoader.moduleNames());
        moduleLoader.startModules();

        logger().info(Constants.PROJECT_NAME + " has been started!");
    }

    /** Stop */
    public static void stop() {
        if (!STARTED) {
            logger().info(Constants.PROJECT_NAME + " has already stopped!");
            return;
        }
        STARTED = false;

        // Stop modules
        logger().info("Stopping modules: " + moduleLoader.moduleNames());
        moduleLoader.stopModules();

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
        start(instance.plugin, instance.server, instance.logger);

        logger().info(Constants.PROJECT_NAME + " has been reloaded!");
    }

    /** Constants used throughout the plugin. */
    public static class Constants {
        public static final String PROJECT_NAME = "TaterLib";
        public static final String PROJECT_ID = "taterlib";
        public static final String PROJECT_VERSION = "1.1.1-SNAPSHOT";
        public static final String PROJECT_AUTHORS = "p0t4t0sandwich";
        public static final String PROJECT_DESCRIPTION =
                "A cross API code library that allows developers to write code that works across multiple modding platforms, and across a wide range of Minecraft versions, all with one JAR file. If TaterLib runs on it, so can your plugin/mod.";
        public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterLib";
    }
}
