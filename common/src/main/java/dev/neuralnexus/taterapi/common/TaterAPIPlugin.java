package dev.neuralnexus.taterapi.common;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * The TaterAPI plugin interface.
 */
public interface TaterAPIPlugin {
    /**
     * Gets the logger.
     */
    Object pluginLogger();

    /**
     * Gets the config path.
     */
    String pluginConfigPath();

    /**
     * Use whatever logger is being used.
     * @param message The message to log
     */
    default void useLogger(String message) {
        Object logger = pluginLogger();

        if (logger instanceof java.util.logging.Logger) {
            ((java.util.logging.Logger) logger).info(message);
        } else if (logger instanceof org.slf4j.Logger) {
            ((org.slf4j.Logger) logger).info(message);
        } else {
            System.out.println(message);
        }
    }

    /**
     * Gets the server type.
     * @return The server type
     */
    default String getServerType() {
        return "unknown";
    }

    /**
     * Register hooks.
     */
    void registerHooks();


    /**
     * Registers event listeners.
     */
    void registerEventListeners();

    /**
     * Registers commands.
     */
    void registerCommands();

    /**
     * Starts the TaterAPI plugin.
     */
    default void pluginStart() {
        runTaskAsync(() -> {
            try {
                useLogger("[TaterAPI] TaterAPI is running on " + getServerType() + "!");

                // Start the TaterAPI
                TaterAPI.start(pluginConfigPath(), pluginLogger());

                // Register hooks
                registerHooks();

                // Register event listeners
                registerEventListeners();

                // Register commands
                registerCommands();

                useLogger("[TaterAPI] TaterAPI has been enabled!");

            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }

    /**
     * Stops the TaterAPI plugin.
     */
    default void pluginStop() {
        runTaskAsync(() -> {
            try {
                TaterAPI.stop();
                useLogger("[TaterAPI] TaterAPI has been disabled!");
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
