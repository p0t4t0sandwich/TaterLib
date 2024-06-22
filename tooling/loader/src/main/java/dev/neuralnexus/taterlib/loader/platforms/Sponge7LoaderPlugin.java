/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.Platform;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

/** Sponge entry point. */
@Plugin(
        id = LoaderImpl.PROJECT_ID,
        name = LoaderImpl.PROJECT_NAME,
        version = LoaderImpl.PROJECT_VERSION,
        description = LoaderImpl.PROJECT_DESCRIPTION)
public class Sponge7LoaderPlugin {
    private static Loader loader;

    @Inject
    public Sponge7LoaderPlugin(PluginContainer container, Logger logger) {
        loader = new LoaderImpl(container, null, logger);
        loader.registerPlugin(plugin());
        if (loader.platform().is(Platform.SPONGE_FORGE)) {
            loader.registerPlugin(ForgeLoaderPlugin.plugin());
        }
        loader.onInit();
    }

    public static dev.neuralnexus.taterlib.plugin.Plugin plugin() {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = "." + MinecraftVersion.V1_8.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_10_2)) {
            version = "." + MinecraftVersion.V1_9.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_11, MinecraftVersion.V1_11_2)) {
            version = "." + MinecraftVersion.V1_11.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_12_2)) {
            version = "." + MinecraftVersion.V1_12.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_12.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".sponge.SpongeTaterLibPlugin";
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

    @Listener
    public void onServerStarted(GameStartedServerEvent event) {
        loader.onEnable();
    }

    @Listener
    public void onServerStopped(GameStoppedServerEvent event) {
        loader.onDisable();
    }
}
