/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms.forge;

import dev.neuralnexus.taterapi.loader.Loader;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ForgeModLifecycleListener_1_13 {
    private final Loader loader;

    public ForgeModLifecycleListener_1_13(Loader loader) {
        this.loader = loader;
    }

    @SubscribeEvent
    public void onInit(FMLCommonSetupEvent event) {
        this.loader.onEnable();
    }
}
