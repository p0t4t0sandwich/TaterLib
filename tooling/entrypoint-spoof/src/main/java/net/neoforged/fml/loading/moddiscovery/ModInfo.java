package net.neoforged.fml.loading.moddiscovery;

import net.neoforged.neoforgespi.language.IModInfo;
import org.apache.maven.artifact.versioning.ArtifactVersion;

/**
 * Fake NeoForge mod info.
 */
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
