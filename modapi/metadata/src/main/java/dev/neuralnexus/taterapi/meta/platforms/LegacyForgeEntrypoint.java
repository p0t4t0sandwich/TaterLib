package dev.neuralnexus.taterapi.meta.platforms;

import cpw.mods.fml.common.Mod;

@Mod(
        modid = "tater_metadata",
        name = "TaterMetadata",
        useMetadata = true,
        acceptableRemoteVersions = "*",
        bukkitPlugin = "TaterMetadata")
public class LegacyForgeEntrypoint {
    public LegacyForgeEntrypoint() {
        TaterMetadata.initForge();
    }
}
