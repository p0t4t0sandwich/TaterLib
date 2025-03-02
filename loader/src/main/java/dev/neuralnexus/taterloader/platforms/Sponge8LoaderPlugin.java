/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterloader.TaterPluginResolver;
import dev.neuralnexus.taterloader.TaterReflectUtil;

import org.spongepowered.api.Server;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/** Sponge entry point. */
@Plugin(LoaderImpl.PROJECT_ID)
public class Sponge8LoaderPlugin {
    private static Loader loader;

    @Inject
    public Sponge8LoaderPlugin(PluginContainer container) {
        MetaAPI.instance().setPrimaryPlatform(Platforms.SPONGE);
        loader = new LoaderImpl(container);
        loader.registerPlugin(TaterPluginResolver.sponge());
        if (MetaAPI.instance().isSpongeForge()) {
            loader.registerPlugin(TaterPluginResolver.forge());
        } else if (MetaAPI.instance().isSpongeFabric()) {
            loader.registerPlugin(TaterPluginResolver.fabric());
        }

        // Set up Vanilla bootstrap, covers Vanilla, Forge, and Fabric paths
        TaterReflectUtil.getRelocatedClass("VanillaBootstrap")
                .ifPresent(
                        className -> {
                            try {
                                Class.forName(className).getMethod("init").invoke(null);
                            } catch (Exception e) {
                                Loader.logger.error(
                                        "Failed to boostrap Vanilla builders/factories", e);
                            }
                        });

        loader.onInit();
    }

    @Listener
    public void onServerStarting(ConstructPluginEvent event) {
        loader.onEnable();
    }

    @Listener
    public void onServerStopping(StoppingEngineEvent<Server> event) {
        loader.onDisable();
    }
}
