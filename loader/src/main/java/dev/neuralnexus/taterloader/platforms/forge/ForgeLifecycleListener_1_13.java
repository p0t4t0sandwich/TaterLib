/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms.forge;

import dev.neuralnexus.taterapi.loader.Loader;

import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeLifecycleListener_1_13 {
    private final Loader loader;

    public ForgeLifecycleListener_1_13(Loader loader) {
        this.loader = loader;
    }

    @SubscribeEvent
    public void onDisable(ServerStoppedEvent event) {
        this.loader.onDisable();
    }
}
