/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.fabricmc.loader.api.metadata;

import net.fabricmc.loader.api.Version;

/** Fake Fabric interface. */
public interface ModMetadata {
    String getId();

    String getName();

    Version getVersion();
}
