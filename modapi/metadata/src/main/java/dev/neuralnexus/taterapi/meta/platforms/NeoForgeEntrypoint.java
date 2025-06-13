package dev.neuralnexus.taterapi.meta.platforms;

import dev.neuralnexus.taterapi.meta.Platforms;

import net.neoforged.fml.common.Mod;

@Mod("tater_metadata")
public class NeoForgeEntrypoint {
    public NeoForgeEntrypoint() {
        TaterMetadata.init(Platforms.NEOFORGE);
    }
}
