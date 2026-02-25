/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms.forge;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public final class ForgeModLifecycleListener_1_13 {
    @SubscribeEvent
    public void onInit(FMLCommonSetupEvent event) {
        TaterLoader.onEnable();
    }
}
