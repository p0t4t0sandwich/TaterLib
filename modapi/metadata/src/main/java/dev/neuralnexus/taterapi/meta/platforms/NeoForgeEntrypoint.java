/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.platforms;

import net.neoforged.fml.common.Mod;

@Mod("tater_metadata")
public class NeoForgeEntrypoint {
    public NeoForgeEntrypoint() {
        TaterMetadata.initNeoForge();
    }
}
