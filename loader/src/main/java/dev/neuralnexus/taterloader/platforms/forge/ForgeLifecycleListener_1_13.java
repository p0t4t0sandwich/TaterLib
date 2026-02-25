/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms.forge;

import dev.neuralnexus.taterloader.TaterLoader;

import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ForgeLifecycleListener_1_13 {
    @SubscribeEvent
    public void onDisable(ServerStoppedEvent event) {
        TaterLoader.onDisable();
    }
}
