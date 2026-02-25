/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
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
