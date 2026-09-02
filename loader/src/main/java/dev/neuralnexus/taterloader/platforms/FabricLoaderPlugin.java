/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
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
