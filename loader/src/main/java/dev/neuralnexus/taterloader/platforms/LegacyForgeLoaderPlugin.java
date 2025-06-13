/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;

import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterapi.loader.Loader;
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
        loader = new LoaderImpl(this);
        loader.registerPlugin(TaterPluginResolver.forge());
        if (MetaAPI.instance().isPlatformPresent(Platforms.BUKKIT)) {
            loader.registerPlugin(TaterPluginResolver.bukkit());
        }
        loader.onInit();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        loader.onEnable();
    }

    @Mod.EventHandler
    public void onDisable(FMLServerStoppedEvent event) {
        loader.onDisable();
    }
}
