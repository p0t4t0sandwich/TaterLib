/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms.forge;

import cpw.mods.fml.common.event.FMLServerStoppedEvent;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public final class ForgeLifecycleListener_1_8 {
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        TaterLoader.onEnable();
    }

    @Mod.EventHandler
    public void onDisable(FMLServerStoppedEvent event) {
        TaterLoader.onDisable();
    }
}
