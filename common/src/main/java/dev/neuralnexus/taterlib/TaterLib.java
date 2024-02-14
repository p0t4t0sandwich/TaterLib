package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.config.ConfigLoader;
import dev.neuralnexus.taterlib.config.dump.DumpInfo;
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
    private AbstractLogger logger;

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
     * @param logger The logger
     */
    public static void start(Object plugin, AbstractLogger logger) {
        setPlugin(plugin);
        setLogger(logger);

        // Config
        ConfigLoader.load();

        // Dump basic debug info
        new DumpInfo().saveDump();

        if (STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register modules
            moduleLoader = new TaterLibModuleLoader();
            moduleLoader.registerModule(new CoreModule());
        }

        // Start modules
        moduleLoader.startModules();

        instance.logger.info(Constants.PROJECT_NAME + " has been started!");
    }

    /** Start */
    public static void start() {
        start(instance.plugin, instance.logger);
    }

    /** Stop */
    public static void stop() {
        if (!STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has already stopped!");
            return;
        }
        STARTED = false;

        // Stop modules
        moduleLoader.stopModules();

        // Remove references to objects

        instance.logger.info(Constants.PROJECT_NAME + " has been stopped!");
        TaterAPIProvider.unregister();
    }

    /** Reload */
    public static void reload() {
        if (!STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Start
        start();

        instance.logger.info(Constants.PROJECT_NAME + " has been reloaded!");
    }

    /** Constants used throughout the plugin. */
    public static class Constants {
        public static final String PROJECT_NAME = "TaterLib";
        public static final String PROJECT_ID = "taterlib";
        public static final String PROJECT_VERSION = "1.1.0-R0.16-SNAPSHOT";
        public static final String PROJECT_AUTHORS = "p0t4t0sandwich";
        public static final String PROJECT_DESCRIPTION =
                "A cross API code library for various generalizations used in the Tater* plugins";
        public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterLib";
    }
}
