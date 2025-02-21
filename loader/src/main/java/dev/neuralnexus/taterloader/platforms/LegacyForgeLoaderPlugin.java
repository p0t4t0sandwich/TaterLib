/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import cpw.mods.fml.common.Mod;

import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.loader.impl.LoaderImpl;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterloader.TaterPluginResolver;

/** Legacy Forge entry point. */
@Mod(
        modid = LoaderImpl.PROJECT_ID,
        name = LoaderImpl.PROJECT_NAME,
        useMetadata = true,
        acceptableRemoteVersions = "*",
        bukkitPlugin = LoaderImpl.PROJECT_NAME)
@SuppressWarnings("FieldCanBeLocal")
public class LegacyForgeLoaderPlugin {
    private static Loader loader;

    public LegacyForgeLoaderPlugin() {
        MetaAPI.instance().setPrimaryPlatform(Platforms.FORGE);
        loader = new LoaderImpl(this);
        loader.registerPlugin(TaterPluginResolver.forge());
        if (MetaAPI.instance().isPlatformPresent(Platforms.BUKKIT)) {
            loader.registerPlugin(TaterPluginResolver.bukkit());
        }
        loader.onInit();
        // TODO: Switch to server-starting? Or switch to common init event?
        loader.onEnable();
        // TODO: Add disable event via the Forge API (will need cross-version abstraction)
    }
}
