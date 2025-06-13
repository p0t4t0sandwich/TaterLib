package dev.neuralnexus.taterapi.meta.platforms;

import net.neoforged.fml.common.Mod;

@Mod("tater_metadata")
public class NeoForgeEntrypoint {
    public NeoForgeEntrypoint() {
        TaterMetadata.initNeoForge();
    }
}
