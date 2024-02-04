package dev.neuralnexus.taterlib.loader.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.v1_20.sponge.SpongeTaterLibPlugin;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

/** Sponge entry point. */
@Plugin(
        id = TaterLib.Constants.PROJECT_ID,
        name = TaterLib.Constants.PROJECT_NAME,
        version = TaterLib.Constants.PROJECT_VERSION,
        description = TaterLib.Constants.PROJECT_DESCRIPTION)
public class Sponge7LoaderPlugin {
    private final Loader loader;

    @Inject
    public Sponge7LoaderPlugin(PluginContainer container, Logger logger) {
        loader = new TaterLibLoader(container, logger);
        loader.registerPlugin(new SpongeTaterLibPlugin());
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
