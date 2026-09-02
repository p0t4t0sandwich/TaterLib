/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package org.spongepowered.plugin;

import org.spongepowered.plugin.metadata.PluginMetadata;

/** Fake Sponge PluginContainer class to simplify the creation of entrypoints. */
public interface PluginContainer {
    PluginMetadata metadata();

    Object instance();
}
