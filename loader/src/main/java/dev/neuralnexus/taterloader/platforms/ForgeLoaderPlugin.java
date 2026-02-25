/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterloader.TaterLoader;
import dev.neuralnexus.taterloader.platforms.forge.ForgeLifecycleListener_1_13;
import dev.neuralnexus.taterloader.platforms.forge.ForgeLifecycleListener_1_8;
import dev.neuralnexus.taterloader.platforms.forge.ForgeModLifecycleListener_1_13;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/** Forge entry point. */
@Mod(
        value = LoaderImpl.PROJECT_ID,
        modid = LoaderImpl.PROJECT_ID,
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions = "*")
@SuppressWarnings("FieldCanBeLocal")
public final class ForgeLoaderPlugin {
    public ForgeLoaderPlugin() {
        if (MetaAPI.instance().version().lessThan(MinecraftVersions.V13)) {
            MinecraftForge.EVENT_BUS.register(new ForgeLifecycleListener_1_8());
        } else {
            MinecraftForge.EVENT_BUS.register(new ForgeLifecycleListener_1_13());
            IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
            if (bus != null) {
                bus.register(new ForgeModLifecycleListener_1_13());
            } else {
                Loader.logger.warn("Failed to register events to mod event bus");
            }
        }
        TaterLoader.onInit();
    }
}
