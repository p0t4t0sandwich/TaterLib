/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.PluginContainer;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

/** Velocity entry point. */
@Plugin(
        id = TaterLoader.MOD_ID,
        name = TaterLoader.MOD_NAME,
        version = TaterLoader.VERSION,
        authors = TaterLoader.AUTHORS,
        description = TaterLoader.DESCRIPTION,
        url = TaterLoader.PROJECT_URL)
public final class VelocityLoaderPlugin {
    @Inject
    public VelocityLoaderPlugin(PluginContainer plugin) {
        TaterLoader.onInit();
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        TaterLoader.onEnable();
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        TaterLoader.onDisable();
    }
}
