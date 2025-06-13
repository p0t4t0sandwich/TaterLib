package dev.neuralnexus.taterapi.meta.platforms;

import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {
    public FabricEntrypoint() {
        TaterMetadata.initFabric();
    }

    @Override
    public void onInitialize() {}
}
