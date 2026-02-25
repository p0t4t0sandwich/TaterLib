/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public final class FabricLoaderPlugin implements ModInitializer {
    public FabricLoaderPlugin() {
        TaterLoader.onInit();
        // TODO: Add disable event via FAPI (might need cross-version abstraction)
    }

    @Override
    public void onInitialize() {
        TaterLoader.onEnable();
    }
}
