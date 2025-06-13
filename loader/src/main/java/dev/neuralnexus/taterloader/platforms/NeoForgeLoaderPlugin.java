/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterloader.TaterPluginResolver;
import dev.neuralnexus.taterloader.platforms.neoforge.NeoForgeLifecycleListener;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;

/** NeoForge entry point. */
@Mod(LoaderImpl.PROJECT_ID)
public class NeoForgeLoaderPlugin {
    private static Loader loader;

    public NeoForgeLoaderPlugin() {
        NeoForge.EVENT_BUS.register(this);

        loader = new LoaderImpl(this);
        IEventBus bus = ModLoadingContext.get().getActiveContainer().getEventBus();
        if (bus != null) {
            bus.register(new NeoForgeLifecycleListener(loader));
        } else {
            Loader.logger.warn("Failed to register events to mod event bus");
        }

        loader.registerPlugin(TaterPluginResolver.neoForge());
        if (MetaAPI.instance().isPlatformPresent(Platforms.BUKKIT)) {
            loader.registerPlugin(TaterPluginResolver.bukkit());
        }
        // Sinytra Connector support
        if (MetaAPI.instance().meta().isModLoaded("connectormod")) {
            loader.registerPlugin(TaterPluginResolver.fabric());
        }
        loader.onInit();
    }

    @SubscribeEvent
    public void onDisable(ServerStoppedEvent event) {
        loader.onDisable();
    }
}
