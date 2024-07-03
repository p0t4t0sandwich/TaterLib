/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.plugin;

/** Module interface. */
public interface PluginModule {
    /**
     * Get the module's id.
     *
     * @return The module's id
     */
    String id();

    /** Enable the module. */
    default void onEnable() {}

    /** Disable the module. */
    default void onDisable() {}
}
