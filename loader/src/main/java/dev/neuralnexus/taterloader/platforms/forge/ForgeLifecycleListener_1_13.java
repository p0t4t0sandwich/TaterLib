/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms.forge;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ForgeLifecycleListener_1_13 {
    @SubscribeEvent
    public void onDisable(ServerStoppedEvent event) {
        TaterLoader.onDisable();
    }
}
