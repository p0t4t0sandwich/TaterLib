/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
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
