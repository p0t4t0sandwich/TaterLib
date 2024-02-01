package dev.neuralnexus.taterlib.loader.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.v1_20.sponge.SpongeTaterLibPlugin;

import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/** Sponge entry point. */
@Plugin(TaterLib.Constants.PROJECT_ID)
public class Sponge8LoaderPlugin {
    private final Loader loader;

    @Inject
    public Sponge8LoaderPlugin(PluginContainer container, Logger logger) {
        loader = new TaterLibLoader(container, logger);
        loader.registerPlugin(new SpongeTaterLibPlugin());
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
