/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterloader.TaterPluginResolver;
import dev.neuralnexus.taterapi.loader.impl.LoaderImpl;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;

/** NeoForge entry point. */
@Mod(LoaderImpl.PROJECT_ID)
public class NeoForgeLoaderPlugin {
    private static Loader loader;

    public NeoForgeLoaderPlugin() {
        MetaAPI.instance().setPrimaryPlatform(Platforms.NEOFORGE);
        NeoForge.EVENT_BUS.register(this);
        loader = new LoaderImpl(this, null);
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
    public void onServerStarted(ServerStartedEvent event) {
        loader.onEnable();
    }

    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        loader.onDisable();
    }
}
