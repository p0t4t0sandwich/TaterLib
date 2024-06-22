package net.minecraftforge.fml.loading.moddiscovery;

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
}
