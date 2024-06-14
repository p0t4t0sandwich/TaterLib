package dev.neuralnexus.taterlib;


import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.bstats.custom.TaterLibMetrics;
import dev.neuralnexus.taterlib.config.TaterLibConfigLoader;
import dev.neuralnexus.taterlib.logger.AbstractLogger;
import dev.neuralnexus.taterlib.modules.core.CoreModule;
import dev.neuralnexus.taterlib.plugin.ModuleLoader;

/** Main class for the plugin. */
public class TaterLib {
    private static final TaterLib instance = new TaterLib();
    private static boolean STARTED = false;
    private static boolean RELOADED = false;
    private static ModuleLoader moduleLoader;
    private Object plugin;
    private Object pluginServer;
    private Object pluginLogger;
    private AbstractLogger logger;

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
     * @param pluginServer The plugin server
     */
    private static void setPluginServer(Object pluginServer) {
        instance.pluginServer = pluginServer;
    }

    /**
     * Set the plugin logger
     *
     * @param pluginLogger The plugin logger
     */
    private static void setPluginLogger(Object pluginLogger) {
        instance.pluginLogger = pluginLogger;
    }

    /**
     * Get the logger
     *
     * @return The logger
     */
    public static AbstractLogger logger() {
        return instance.logger;
    }

    /**
     * Set the logger
     *
     * @param logger The logger
     */
    private static void setLogger(AbstractLogger logger) {
        instance.logger = logger;
    }

    /**
     * Start
     *
     * @param plugin The plugin
     * @param pluginServer The plugin server
     * @param pluginLogger The plugin logger
     * @param logger The logger
     */
    public static void start(
            Object plugin, Object pluginServer, Object pluginLogger, AbstractLogger logger) {
        if (pluginServer != null) {
            setPluginServer(pluginServer);
        }
        if (pluginLogger != null) {
            setPluginLogger(pluginLogger);
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
        }

        // Start modules
        logger().info("Starting modules: " + moduleLoader.moduleNames());
        moduleLoader.startModules();

        logger().info(Constants.PROJECT_NAME + " has been started!");
    }

    /** Start */
    public static void start() {
        start(instance.plugin, instance.pluginServer, instance.pluginLogger, instance.logger);
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
        start();

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
