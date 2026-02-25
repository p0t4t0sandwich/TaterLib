/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;

import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterloader.TaterLoader;

/** Legacy Forge entry point. */
@Mod(
        modid = LoaderImpl.PROJECT_ID,
        name = LoaderImpl.PROJECT_NAME,
        useMetadata = true,
        acceptableRemoteVersions = "*",
        bukkitPlugin = LoaderImpl.PROJECT_NAME)
@SuppressWarnings("FieldCanBeLocal")
public final class LegacyForgeLoaderPlugin {
    public LegacyForgeLoaderPlugin() {
        TaterLoader.onInit();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        TaterLoader.onEnable();
    }

    @Mod.EventHandler
    public void onDisable(FMLServerStoppedEvent event) {
        TaterLoader.onDisable();
    }
}
