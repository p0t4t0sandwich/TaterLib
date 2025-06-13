package dev.neuralnexus.taterapi.meta.platforms;

import dev.neuralnexus.taterapi.meta.Platforms;

import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {
    public FabricEntrypoint() {
        TaterMetadata.init(Platforms.FABRIC);
    }

    @Override
    public void onInitialize() {}
}
