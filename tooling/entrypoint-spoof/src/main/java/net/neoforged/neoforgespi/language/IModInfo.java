package net.neoforged.neoforgespi.language;

import org.apache.maven.artifact.versioning.ArtifactVersion;

/** Fake NeoForge mod info. */
public interface IModInfo {
    String getModId();

    String getDisplayName();

    ArtifactVersion getVersion();
}
