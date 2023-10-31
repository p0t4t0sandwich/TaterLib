package dev.neuralnexus.taterlib.common;

import dev.neuralnexus.taterlib.common.logger.AbstractLogger;

public interface TemplatePlugin {
    /**
     * Gets the config path.
     */
    String pluginConfigPath();

    /**
     * Get the plugin logger.
     */
    AbstractLogger pluginLogger();

    /**
     * Use whatever logger is being used.
     * @param message The message to log
     */
    default void useLogger(String message) {
        pluginLogger().info(message);
    }

    /**
     * Gets the server type.
     * @return The server type
     */
    default String getServerType() {
        return "unknown";
    }

    /**
     * Gets the server version.
     * @return The server version
     */
    default String getServerVersion() {
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
}
