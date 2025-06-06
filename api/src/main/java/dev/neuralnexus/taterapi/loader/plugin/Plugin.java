/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.loader.plugin;

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

    /** Fires when the platform first initializes, doesn't have a common use case */
    default void onInit() {}

    /** Fires when the plugin is enabled. */
    default void onEnable() {}

    /** Fires when the plugin is disabled. */
    default void onDisable() {}
}
