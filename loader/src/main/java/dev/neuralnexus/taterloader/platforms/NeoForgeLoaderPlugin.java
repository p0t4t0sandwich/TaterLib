/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;
import dev.neuralnexus.taterloader.platforms.neoforge.NeoForgeLifecycleListener;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;

/** NeoForge entry point. */
@Mod(TaterLoader.MOD_ID)
public final class NeoForgeLoaderPlugin {
    public NeoForgeLoaderPlugin() {
        NeoForge.EVENT_BUS.register(this);
        IEventBus bus = ModLoadingContext.get().getActiveContainer().getEventBus();
        if (bus != null) {
            bus.register(new NeoForgeLifecycleListener());
        } else {
            TaterLoader.logger.warn("Failed to register events to mod event bus");
        }
        TaterLoader.onInit();
    }

    @SubscribeEvent
    public void onDisable(ServerStoppedEvent event) {
        TaterLoader.onDisable();
    }
}
