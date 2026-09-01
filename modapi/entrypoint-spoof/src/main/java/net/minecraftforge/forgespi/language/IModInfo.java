/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.forgespi.language;

import org.apache.maven.artifact.versioning.ArtifactVersion;

/** Fake Forge mod info. */
public interface IModInfo {
    String getModId();

    String getDisplayName();

    ArtifactVersion getVersion();

    IModFileInfo getOwningFile();
}
