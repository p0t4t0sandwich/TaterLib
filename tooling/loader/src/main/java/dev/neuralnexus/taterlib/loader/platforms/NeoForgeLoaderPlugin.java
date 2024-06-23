/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import com.mojang.logging.LogUtils;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;
import dev.neuralnexus.taterlib.plugin.Plugin;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;

/** NeoForge entry point. */
@Mod(LoaderImpl.PROJECT_ID)
public class NeoForgeLoaderPlugin {
    private static Loader loader;

    public NeoForgeLoaderPlugin() {
        NeoForge.EVENT_BUS.register(this);
        loader = new LoaderImpl(this, null, LogUtils.getLogger());
        loader.registerPlugin(plugin());
        loader.onInit();
    }

    public static Plugin plugin() {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_21)) {
            version = "." + MinecraftVersion.V1_20_2.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_20_2.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".neoforge.NeoForgeTaterLibPlugin";
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

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        loader.onEnable();
    }

    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        loader.onDisable();
    }
}
