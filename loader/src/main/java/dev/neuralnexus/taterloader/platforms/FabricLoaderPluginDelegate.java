/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterloader.TaterLoader;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;

public final class FabricLoaderPluginDelegate
        implements ClientModInitializer, DedicatedServerModInitializer {
    private boolean hasInitialized = false;

    @Override
    public void onInitializeClient() {
        if (this.hasInitialized) {
            return;
        }
        this.hasInitialized = true;
        TaterLoader.onEnable();
    }

    @Override
    public void onInitializeServer() {
        if (this.hasInitialized) {
            return;
        }
        this.hasInitialized = true;
        TaterLoader.onEnable();
    }
}
