/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package org.spongepowered.plugin;

import org.spongepowered.plugin.metadata.PluginMetadata;

import java.util.Optional;

/** Fake Sponge PluginContainer class to simplify the creation of entrypoints. */
public interface PluginContainer {
    PluginMetadata metadata();

    Object instance();
}
