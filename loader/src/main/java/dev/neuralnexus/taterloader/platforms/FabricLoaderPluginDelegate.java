/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

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
