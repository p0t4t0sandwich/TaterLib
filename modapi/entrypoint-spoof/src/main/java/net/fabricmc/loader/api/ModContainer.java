/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.fabricmc.loader.api;

import net.fabricmc.loader.api.metadata.ModMetadata;

import java.nio.file.Path;
import java.util.List;

/** Fake Fabric interface. */
public interface ModContainer {
    ModMetadata getMetadata();

    List<Path> getRootPaths();
}
