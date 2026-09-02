/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package com.velocitypowered.api.plugin;

import java.nio.file.Path;
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

    default Optional<Path> getSource() {
        return Optional.empty();
    }
}
