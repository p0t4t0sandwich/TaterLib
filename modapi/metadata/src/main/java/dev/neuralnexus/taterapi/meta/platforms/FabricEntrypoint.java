/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.platforms;

import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {
    public FabricEntrypoint() {
        TaterMetadata.initFabric();
    }

    @Override
    public void onInitialize() {}
}
