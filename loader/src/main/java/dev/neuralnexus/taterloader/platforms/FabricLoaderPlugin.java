/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterloader.TaterPluginResolver;

import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public class FabricLoaderPlugin implements ModInitializer {
    protected static Loader loader;

    public FabricLoaderPlugin() {
        MetaAPI.instance().setPrimaryPlatform(Platforms.FABRIC);
        loader = new LoaderImpl(this);
        loader.registerPlugin(TaterPluginResolver.fabric());
        if (MetaAPI.instance().isPlatformPresent(Platforms.BUKKIT)) {
            loader.registerPlugin(TaterPluginResolver.bukkit());
        }
        // Kilt support
        if (MetaAPI.instance().meta().isModLoaded("kilt")) {
            loader.registerPlugin(TaterPluginResolver.neoForge());
        }
        loader.onInit();
        // TODO: Add disable event via FAPI (might need cross-version abstraction)
    }

    @Override
    public void onInitialize() {}
}
