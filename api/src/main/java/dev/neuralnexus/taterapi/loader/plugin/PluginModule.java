/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.loader.plugin;

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
