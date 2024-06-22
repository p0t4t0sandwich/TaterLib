/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;
import dev.neuralnexus.taterlib.plugin.Plugin;

import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public class FabricLoaderPlugin implements ModInitializer {
    private static Loader loader;

    public FabricLoaderPlugin() {
        loader = new LoaderImpl(this, null, null);
        loader.registerPlugin(plugin());
        if (loader.platform().isFabricHybrid()) {
            loader.registerPlugin(BukkitLoaderPlugin.plugin());
        }
        loader.onInit();
    }

    public static Plugin plugin() {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_7_2, MinecraftVersion.V1_7_10)) {
            version = "." + MinecraftVersion.V1_7_10.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = "." + MinecraftVersion.V1_8_9.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_9_4)) {
            version = "." + MinecraftVersion.V1_9_4.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_10, MinecraftVersion.V1_10_2)) {
            version = "." + MinecraftVersion.V1_10_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_11, MinecraftVersion.V1_11_2)) {
            version = "." + MinecraftVersion.V1_11_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_12_2)) {
            version = "." + MinecraftVersion.V1_12_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_13, MinecraftVersion.V1_14_4)) {
            version = "." + MinecraftVersion.V1_14.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_15, MinecraftVersion.V1_15_2)) {
            version = "." + MinecraftVersion.V1_15.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_16_5)) {
            version = "." + MinecraftVersion.V1_16.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_17, MinecraftVersion.V1_17_1)) {
            version = "." + MinecraftVersion.V1_17.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_18, MinecraftVersion.V1_18_2)) {
            version = "." + MinecraftVersion.V1_18.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_19, MinecraftVersion.V1_19_4)) {
            version = "." + MinecraftVersion.V1_19.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_20)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        } else if (mcv.is(MinecraftVersion.V1_21)) {
            version = "." + MinecraftVersion.V1_21.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".fabric.FabricTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            return (Plugin) pluginClass.getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to load plugin class: " + pluginClassName);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onInitialize() {
        loader.onEnable();
    }
}
