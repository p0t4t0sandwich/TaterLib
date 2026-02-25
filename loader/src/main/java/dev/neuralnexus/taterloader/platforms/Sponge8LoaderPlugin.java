/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

import org.spongepowered.api.Server;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/** Sponge entry point. */
@Plugin(TaterLoader.MOD_ID)
public final class Sponge8LoaderPlugin {
    @Inject
    public Sponge8LoaderPlugin(PluginContainer container) {
        // Set up Vanilla bootstrap, covers Vanilla, Forge, and Fabric paths
        // TODO: Handle with service loader?
        // TaterReflectUtil.getRelocatedClass("VanillaBootstrap")
        //    .ifPresent(
        //        className -> {
        //            try {
        //                Class.forName(className).getMethod("init").invoke(null);
        //            } catch (Exception e) {
        //                Loader.logger.error(
        //                    "Failed to boostrap Vanilla builders/factories", e);
        //            }
        //        });
        TaterLoader.onInit();
    }

    @Listener
    public void onServerStarting(ConstructPluginEvent event) {
        TaterLoader.onEnable();
    }

    @Listener
    public void onServerStopping(StoppingEngineEvent<Server> event) {
        TaterLoader.onDisable();
    }
}
