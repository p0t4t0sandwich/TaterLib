/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package org.spongepowered.api.plugin;

import java.nio.file.Path;
import java.util.Optional;

/** Fake Sponge PluginContainer class to simplify the creation of entrypoints. */
public interface PluginContainer {
    String getName();

    String getId();

    Optional<String> getVersion();

    Optional<?> getInstance();

    default Optional<Path> getSource() {
        return Optional.empty();
    }
}
