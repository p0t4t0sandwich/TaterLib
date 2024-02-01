package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.v1_20.fabric.FabricTaterLibPlugin;

import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public class FabricLoaderPlugin implements ModInitializer {
    private final Loader loader;

    public FabricLoaderPlugin() {
        loader = new TaterLibLoader(this, null);
        loader.registerPlugin(new FabricTaterLibPlugin());
        loader.onInit();
    }

    @Override
    public void onInitialize() {
        loader.onEnable();
    }
}
