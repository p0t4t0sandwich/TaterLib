/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms.neoforge;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

public final class NeoForgeLifecycleListener {
    @SubscribeEvent
    public void onInit(FMLCommonSetupEvent event) {
        TaterLoader.onEnable();
    }
}
