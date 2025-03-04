/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package org.spongepowered.plugin;

import org.spongepowered.plugin.metadata.PluginMetadata;

/** Fake Sponge PluginContainer class to simplify the creation of entrypoints. */
public interface PluginContainer {
    PluginMetadata metadata();

    Object instance();
}
