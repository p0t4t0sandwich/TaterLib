/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

/** Sponge entry point. */
@Plugin(
        id = TaterLoader.MOD_ID,
        name = TaterLoader.MOD_NAME,
        version = TaterLoader.VERSION,
        description = TaterLoader.DESCRIPTION)
public final class Sponge7LoaderPlugin {
    @Inject
    public Sponge7LoaderPlugin(PluginContainer container) {
        TaterLoader.onInit();
    }

    // TODO: Switch to server-starting? Or switch to common init event?
    @Listener
    public void onServerStarted(GameStartedServerEvent event) {
        TaterLoader.onEnable();
    }

    @Listener
    public void onServerStopped(GameStoppedServerEvent event) {
        TaterLoader.onDisable();
    }
}
