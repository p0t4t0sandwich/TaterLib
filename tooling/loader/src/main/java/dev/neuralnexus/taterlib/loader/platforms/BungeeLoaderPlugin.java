/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

/** Bungee entry point. */
public class BungeeLoaderPlugin extends Plugin {
    private static Loader loader;

    public BungeeLoaderPlugin() {
        loader = new LoaderImpl(this, ProxyServer.getInstance(), getLogger());
        loader.registerPlugin(getPlugin());
        loader.onInit();
    }

    public static dev.neuralnexus.taterlib.plugin.Plugin getPlugin() {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_4_2, MinecraftVersion.V1_7_10)) {
            version = "." + MinecraftVersion.V1_4_7.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_11_2)) {
            version = "." + MinecraftVersion.V1_8.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_15_2)) {
            version = "." + MinecraftVersion.V1_12.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_21)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".bungee.BungeeTaterLibPlugin";
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

    @Override
    public void onEnable() {
        loader.onEnable();
    }

    @Override
    public void onDisable() {
        loader.onDisable();
    }
}
