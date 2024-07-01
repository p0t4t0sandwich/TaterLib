/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.plugin;

import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.logger.Logger;

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

    /**
     * Get the plugin's logger.
     *
     * @return The plugin's logger.
     */
    default Logger logger() {
        return Loader.instance().logger(id());
    }

    /** Start the plugin. */
    void start();

    /** Stop the plugin. */
    default void stop() {}

    /** Initialize platform-specific implementations. */
    default void onInit() {}

    /** Enable platform-specific implementations. */
    default void onEnable() {}

    /** Disable platform-specific implementations. */
    default void onDisable() {}
}
