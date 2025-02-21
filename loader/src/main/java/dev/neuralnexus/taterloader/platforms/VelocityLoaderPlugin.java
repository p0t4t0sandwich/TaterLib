/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.PluginContainer;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.loader.impl.LoaderImpl;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterloader.TaterPluginResolver;

/** Velocity entry point. */
@Plugin(
        id = LoaderImpl.PROJECT_ID,
        name = LoaderImpl.PROJECT_NAME,
        version = LoaderImpl.PROJECT_VERSION,
        authors = LoaderImpl.PROJECT_AUTHORS,
        description = LoaderImpl.PROJECT_DESCRIPTION,
        url = LoaderImpl.PROJECT_URL)
public class VelocityLoaderPlugin {
    private static Loader loader;

    @Inject
    public VelocityLoaderPlugin(PluginContainer plugin) {
        MetaAPI.instance().setPrimaryPlatform(Platforms.VELOCITY);
        loader = new LoaderImpl(plugin);
        loader.registerPlugin(TaterPluginResolver.velocity());
        loader.onInit();
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        loader.onEnable();
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        loader.onDisable();
    }
}
