/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.fabricmc.loader.api;

import net.fabricmc.loader.api.metadata.ModMetadata;

/** Fake Fabric interface. */
public interface ModContainer {
    ModMetadata getMetadata();
}
