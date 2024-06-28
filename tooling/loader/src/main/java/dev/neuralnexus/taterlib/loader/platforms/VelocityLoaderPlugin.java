/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;

import org.slf4j.Logger;

/** Velocity entry point. */
// @Plugin(
//        id = LoaderImpl.PROJECT_ID,
//        name = LoaderImpl.PROJECT_NAME,
//        version = LoaderImpl.PROJECT_VERSION,
//        authors = LoaderImpl.PROJECT_AUTHORS,
//        description = LoaderImpl.PROJECT_DESCRIPTION,
//        url = LoaderImpl.PROJECT_URL)
public class VelocityLoaderPlugin {
    private static Loader loader;

    @Inject
    public VelocityLoaderPlugin(PluginContainer plugin, ProxyServer server, Logger logger) {
        TaterAPIProvider.setPrimaryPlatform(Platform.VELOCITY);
        loader = new LoaderImpl(plugin, server, logger);
        loader.registerPlugin(plugin());
        loader.onInit();
    }

    public static dev.neuralnexus.taterlib.plugin.Plugin plugin() {
        String pluginClassName = "dev.neuralnexus.taterlib.velocity.v3_3_0.VelocityTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            return (dev.neuralnexus.taterlib.plugin.Plugin)
                    pluginClass.getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to load plugin class: " + pluginClassName);
            e.printStackTrace();
        }
        return null;
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
