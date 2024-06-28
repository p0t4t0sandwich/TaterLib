/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;

import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/** Sponge entry point. */
@Plugin(LoaderImpl.PROJECT_ID)
public class Sponge8LoaderPlugin {
    private static Loader loader;

    @Inject
    public Sponge8LoaderPlugin(PluginContainer container, Logger logger) {
        TaterAPIProvider.setPrimaryPlatform(Platform.SPONGE);
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
        if (mcv.isInRange(MinecraftVersion.V1_13, MinecraftVersion.V1_16_5)) {
            version = "." + MinecraftVersion.V1_13.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_17, MinecraftVersion.V1_18_2)) {
            version = "." + MinecraftVersion.V1_17.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_19, MinecraftVersion.V1_19_4)) {
            version = "." + MinecraftVersion.V1_19.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_21)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
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
    public void onServerStarting(ConstructPluginEvent event) {
        loader.onEnable();
    }

    @Listener
    public void onServerStopping(StoppingEngineEvent<Server> event) {
        loader.onDisable();
    }
}
