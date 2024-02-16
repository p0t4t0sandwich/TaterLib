package dev.neuralnexus.taterlib.plugin;

import dev.neuralnexus.taterlib.logger.AbstractLogger;

/** General plugin interface. */
public interface Plugin {
    /**
     * Get the name of the plugin.
     *
     * @return The name of the plugin.
     */
    String name();

    /**
     * Get the plugin's id.
     *
     * @return The plugin's id.
     */
    String id();

    /** Start the plugin. */
    void pluginStart(
            Object plugin, Object pluginServer, Object pluginLogger, AbstractLogger logger);

    /** Stop the plugin. */
    void pluginStop();

    /** Initialize platform-specific implementations. */
    default void platformInit(Object plugin, Object server, Object logger) {}

    /** Enable platform-specific implementations. */
    default void platformEnable() {}

    /** Disable platform-specific implementations. */
    default void platformDisable() {}
}
