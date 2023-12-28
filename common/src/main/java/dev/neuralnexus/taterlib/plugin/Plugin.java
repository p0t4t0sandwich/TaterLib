package dev.neuralnexus.taterlib.plugin;

import dev.neuralnexus.taterlib.logger.AbstractLogger;

/** General plugin interface. */
public interface Plugin {
    /**
     * Get the name of the plugin.
     *
     * @return The name of the plugin.
     */
    String getName();

    /**
     * Get the plugin's id.
     *
     * @return The plugin's id.
     */
    String getId();

    /** Start the plugin. */
    void pluginStart(Object plugin, AbstractLogger logger);

    /** Stop the plugin. */
    void pluginStop();

    /** Initialize platform-specific implementations. */
    void platformInit(Object plugin, Object logger);

    /** Enable platform-specific implementations. */
    default void platformEnable() {}

    /** Disable platform-specific implementations. */
    default void platformDisable() {}
}
