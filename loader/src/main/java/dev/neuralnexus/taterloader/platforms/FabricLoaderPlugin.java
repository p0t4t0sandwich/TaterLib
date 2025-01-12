/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.loader.impl.LoaderImpl;
import dev.neuralnexus.taterloader.TaterPluginResolver;

import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public class FabricLoaderPlugin implements ModInitializer {
    private static Loader loader;

    public FabricLoaderPlugin() {
        MetaAPI.instance().setPrimaryPlatform(Platforms.FABRIC);
        loader = new LoaderImpl(this, null);
        loader.registerPlugin(TaterPluginResolver.fabric());
        if (MetaAPI.instance().isPlatformPresent(Platforms.BUKKIT)) {
            loader.registerPlugin(TaterPluginResolver.bukkit());
        }
        // Kilt support
        if (MetaAPI.instance().meta().isModLoaded("kilt")) {
            loader.registerPlugin(TaterPluginResolver.neoForge());
        }
        loader.onInit();
    }

    @Override
    public void onInitialize() {
        loader.onEnable();
    }
}
