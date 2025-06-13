/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.platforms.TaterMetadata;
import dev.neuralnexus.taterloader.TaterPluginResolver;

import net.md_5.bungee.api.plugin.Plugin;

/** Bungee entry point. */
public class BungeeCordLoaderPlugin extends Plugin {
    private static Loader loader;

    public BungeeCordLoaderPlugin() {
        TaterMetadata.init(Platforms.BUNGEECORD);
        loader = new LoaderImpl(this);
        loader.registerPlugin(TaterPluginResolver.bungeeCord());
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
