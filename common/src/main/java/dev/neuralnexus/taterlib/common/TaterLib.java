package dev.neuralnexus.taterlib.common;

import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.common.listeners.command.CommandListener;
import dev.neuralnexus.taterlib.common.logger.AbstractLogger;
import dev.neuralnexus.taterlib.common.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;

public class TaterLib {
    private static final TaterLib instance = new TaterLib();
    private Object plugin;
    private AbstractLogger logger;
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    /**
     * Getter for the singleton instance of the class.
     * @return The singleton instance
     */
    public static TaterLib getInstance() {
        return instance;
    }

    /**
     * Set the plugin
     * @param plugin The plugin
     */
    private static void setPlugin(Object plugin) {
        instance.plugin = plugin;
    }

    /**
     * Get the plugin
     * @return The plugin
     */
    public static Object getPlugin() {
        return instance.plugin;
    }

    /**
     * Set the logger
     * @param logger The logger
     */
    private static void setLogger(AbstractLogger logger) {
        instance.logger = logger;
    }

    /**
     * Get the logger
     * @return The logger
     */
    public static AbstractLogger getLogger() {
        return instance.logger;
    }

    /**
     * Start
     * @param plugin The plugin
     * @param logger The logger
     */
    public static void start(Object plugin, AbstractLogger logger) {
        setPlugin(plugin);
        setLogger(logger);

        if (STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            TaterAPI api = TaterAPIProvider.get();

            // Register hooks
            ServerEvents.STARTED.register(event -> {
                // Register LuckPerms hook
                if (api.isPluginLoaded("LuckPerms")) {
                    instance.logger.info("LuckPerms detected, enabling LuckPerms hook.");
                    api.addHook("luckperms", new LuckPermsHook());
                }
            });

            // Register player listeners
            PlayerEvents.LOGIN.register(PlayerListener::onPlayerLogin);
            PlayerEvents.LOGOUT.register(PlayerListener::onPlayerLogout);
            PlayerEvents.SERVER_SWITCH.register(PlayerListener::onServerSwitch);

            // Register commands
            if (TaterAPIProvider.get().isBrigadierSupported()) {
                CommandEvents.REGISTER_BRIGADIER_COMMAND.register(CommandListener::onRegisterBrigadierCommand);
            } else {
                CommandEvents.REGISTER_COMMAND.register(CommandListener::onRegisterCommand);
            }
        }

        instance.logger.info(Constants.PROJECT_NAME + " has been started!");
    }

    /**
     * Start
     */
    public static void start() {
        start(instance.plugin, instance.logger);
    }

    /**
     * Stop
     */
    public static void stop() {
        if (!STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has already stopped!");
            return;
        }
        STARTED = false;

        instance.logger.info(Constants.PROJECT_NAME + " has been stopped!");
        TaterAPIProvider.unregister();
    }

    /**
     * Reload
     */
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

    /**
     * Constants used throughout the plugin.
     */
    public static class Constants {
        public static final String PROJECT_NAME = "TaterLib";
        public static final String PROJECT_ID = "taterlib";
        public static final String PROJECT_VERSION = "1.1.0-R0.3-SNAPSHOT";
        public static final String PROJECT_AUTHORS = "p0t4t0sandwich";
        public static final String PROJECT_DESCRIPTION = "A cross API code library for various generalizations used in the Tater* plugins";
        public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterLib";
    }
}
