/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterloader.TaterPluginResolver;

import net.minecraftforge.fml.common.Mod;

/** Forge entry point. */
@Mod(
        value = LoaderImpl.PROJECT_ID,
        modid = LoaderImpl.PROJECT_ID,
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions = "*")
@SuppressWarnings("FieldCanBeLocal")
public class ForgeLoaderPlugin {
    private static Loader loader;

    public ForgeLoaderPlugin() {
        MetaAPI.instance().setPrimaryPlatform(Platforms.FORGE);
        loader = new LoaderImpl(this);
        loader.registerPlugin(TaterPluginResolver.forge());
        if (MetaAPI.instance().isPlatformPresent(Platforms.BUKKIT)) {
            loader.registerPlugin(TaterPluginResolver.bukkit());
        }
        // Sinytra Connector support
        if (MetaAPI.instance().meta().isModLoaded("connectormod")) {
            loader.registerPlugin(TaterPluginResolver.fabric());
        }
        loader.onInit();
        // TODO: Switch to server-starting? Or switch to common init event?
        loader.onEnable();
        // TODO: Add disable event via the Forge API (will need cross-version abstraction)
    }
}
