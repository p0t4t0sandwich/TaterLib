/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.TaterPluginResolver;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

/** Bungee entry point. */
public class BungeeCordLoaderPlugin extends Plugin {
    private static Loader loader;

    public BungeeCordLoaderPlugin() {
        TaterAPIProvider.setPrimaryPlatform(Platform.BUNGEECORD);
        loader = new LoaderImpl(this, ProxyServer.getInstance(), getLogger());
        loader.registerPlugin(TaterPluginResolver.bungeeCord(loader));
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
