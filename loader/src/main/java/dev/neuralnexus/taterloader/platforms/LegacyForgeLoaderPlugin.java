/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

/** Legacy Forge entry point. */
@Mod(
        modid = TaterLoader.MOD_ID,
        name = TaterLoader.MOD_NAME,
        useMetadata = true,
        acceptableRemoteVersions = "*",
        bukkitPlugin = TaterLoader.MOD_NAME)
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
