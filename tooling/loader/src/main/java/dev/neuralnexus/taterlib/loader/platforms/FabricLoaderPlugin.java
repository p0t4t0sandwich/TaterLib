/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.TaterPluginResolver;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;

import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public class FabricLoaderPlugin implements ModInitializer {
    private static Loader loader;

    public FabricLoaderPlugin() {
        TaterAPIProvider.setPrimaryPlatform(Platform.FABRIC);
        loader = new LoaderImpl(this, null);
        loader.registerPlugin(TaterPluginResolver.fabric(loader));
        if (loader.platform().isFabricHybrid()) {
            loader.registerPlugin(TaterPluginResolver.bukkit(loader));
        }
        // Kilt support
        if (loader.platformData().isModLoaded("kilt")) {
            loader.registerPlugin(TaterPluginResolver.neoForge(loader));
        }
        loader.onInit();
    }

    @Override
    public void onInitialize() {
        loader.onEnable();
    }
}
