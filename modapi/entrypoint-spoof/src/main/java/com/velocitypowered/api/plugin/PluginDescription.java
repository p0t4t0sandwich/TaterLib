/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package com.velocitypowered.api.plugin;

import java.util.Optional;

/** Fake Velocity plugin description interface. */
public interface PluginDescription {
    String getId();

    default Optional<String> getName() {
        return Optional.empty();
    }

    default Optional<String> getVersion() {
        return Optional.empty();
    }

    default Optional<String> getDescription() {
        return Optional.empty();
    }
}
