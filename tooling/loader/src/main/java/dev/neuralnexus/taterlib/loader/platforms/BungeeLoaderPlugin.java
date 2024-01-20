package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.bungee.BungeeTaterLibPlugin;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;

import net.md_5.bungee.api.plugin.Plugin;

/** Bungee entry point. */
public class BungeeLoaderPlugin extends Plugin {
    private final Loader loader;

    public BungeeLoaderPlugin() {
        loader = new TaterLibLoader(this, getLogger());
        loader.registerPlugin(new BungeeTaterLibPlugin());
        loader.onInit();
    }

    @Override
    public void onEnable() {
        loader.onEnable();
    }

    @Override
    public void onDisable() {
        loader.onDisable();
    }
}
