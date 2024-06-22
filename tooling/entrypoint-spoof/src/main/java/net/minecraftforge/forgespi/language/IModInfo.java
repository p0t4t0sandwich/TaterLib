package net.minecraftforge.forgespi.language;

import org.apache.maven.artifact.versioning.ArtifactVersion;

/** Fake Forge mod info. */
public interface IModInfo {
    String getModId();

    String getDisplayName();

    ArtifactVersion getVersion();
}
