/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package org.spongepowered.plugin.metadata;

import java.util.Optional;

/** Fake Sponge interface */
public interface PluginMetadata extends Inheritable {
    String id();

    Optional<String> name();
}
