/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;

public class FabricLoaderPluginDelegate
        implements ClientModInitializer, DedicatedServerModInitializer {
    private boolean hasInitialized = false;

    @Override
    public void onInitializeClient() {
        if (this.hasInitialized) {
            return;
        }
        this.hasInitialized = true;
        FabricLoaderPlugin.loader.onEnable();
    }

    @Override
    public void onInitializeServer() {
        if (this.hasInitialized) {
            return;
        }
        this.hasInitialized = true;
        FabricLoaderPlugin.loader.onEnable();
    }
}
