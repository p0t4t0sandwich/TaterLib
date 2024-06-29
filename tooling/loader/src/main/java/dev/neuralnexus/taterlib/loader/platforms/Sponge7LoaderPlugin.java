/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.TaterPluginResolver;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;

import org.slf4j.Logger;
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
    public Sponge7LoaderPlugin(PluginContainer container, Logger logger) {
        TaterAPIProvider.setPrimaryPlatform(Platform.SPONGE);
        loader = new LoaderImpl(container, null, logger);
        loader.registerPlugin(TaterPluginResolver.sponge7(loader));
        if (Platform.isForge()) {
            loader.registerPlugin(TaterPluginResolver.forge(loader));
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
