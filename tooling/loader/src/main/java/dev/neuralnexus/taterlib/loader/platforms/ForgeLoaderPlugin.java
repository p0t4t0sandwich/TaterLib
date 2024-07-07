/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.TaterPluginResolver;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;

import net.minecraftforge.fml.common.Mod;

/** Forge entry point. */
@Mod(
        value = LoaderImpl.PROJECT_ID,
        modid = LoaderImpl.PROJECT_ID,
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions = "*")
public class ForgeLoaderPlugin {
    private static Loader loader;

    public ForgeLoaderPlugin() {
        TaterAPIProvider.setPrimaryPlatform(Platform.FORGE);
        loader = new LoaderImpl(this, null);
        loader.registerPlugin(TaterPluginResolver.forge(loader));
        if (loader.platform().isForgeHybrid()) {
            loader.registerPlugin(TaterPluginResolver.bukkit(loader));
        }
        // Sinytra Connector support
        if (loader.platformData().isModLoaded("connectormod")) {
            loader.registerPlugin(TaterPluginResolver.fabric(loader));
        }
        loader.onInit();
        loader.onEnable();
    }
}
