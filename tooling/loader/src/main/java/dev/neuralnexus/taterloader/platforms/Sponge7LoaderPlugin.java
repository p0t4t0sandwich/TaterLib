/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterloader.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterloader.Loader;
import dev.neuralnexus.taterloader.TaterPluginResolver;
import dev.neuralnexus.taterloader.impl.LoaderImpl;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

/** Sponge entry point. */
@Plugin(
        id = LoaderImpl.PROJECT_ID,
        name = LoaderImpl.PROJECT_NAME,
        version = LoaderImpl.PROJECT_VERSION,
        description = LoaderImpl.PROJECT_DESCRIPTION)
public class Sponge7LoaderPlugin {
    private static Loader loader;

    @Inject
    public Sponge7LoaderPlugin(PluginContainer container) {
        TaterAPIProvider.setPrimaryPlatform(Platform.SPONGE);
        loader = new LoaderImpl(container, null);
        loader.registerPlugin(TaterPluginResolver.sponge());
        if (loader.platform().isForgeBased()) {
            loader.registerPlugin(TaterPluginResolver.forge());
        }
        loader.onInit();
    }

    @Listener
    public void onServerStarted(GameStartedServerEvent event) {
        loader.onEnable();
    }

    @Listener
    public void onServerStopped(GameStoppedServerEvent event) {
        loader.onDisable();
    }
}
