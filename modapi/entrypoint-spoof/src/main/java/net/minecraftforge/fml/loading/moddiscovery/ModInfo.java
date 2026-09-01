/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.fml.loading.moddiscovery;

import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.language.IModInfo;

import org.apache.maven.artifact.versioning.ArtifactVersion;

/** Fake Forge mod info. */
public class ModInfo implements IModInfo {
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

    @Override
    public IModFileInfo getOwningFile() {
        return null;
    }
}
