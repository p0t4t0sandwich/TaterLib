/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.neoforged.fml.loading.moddiscovery;

import net.neoforged.neoforgespi.language.IModInfo;

import org.apache.maven.artifact.versioning.ArtifactVersion;

/** Fake NeoForge mod info. */
public abstract class ModInfo implements IModInfo {
    @Override
    public String getModId() {
        return "";
    }

    @Override
    public String getDisplayName() {
        return "";
    }

    @Override
    public ArtifactVersion getVersion() {
        return new ArtifactVersion() {};
    }
}
