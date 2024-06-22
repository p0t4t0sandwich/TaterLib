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

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitLoaderPlugin extends JavaPlugin {
    private static Loader loader;

    public BukkitLoaderPlugin() {
        loader = new LoaderImpl(this, Bukkit.getServer(), Bukkit.getLogger());
        loader.registerPlugin(plugin());
        if (loader.platform().isForgeHybrid()) {
            loader.registerPlugin(ForgeLoaderPlugin.plugin());
        } else if (loader.platform().isFabricHybrid()) {
            loader.registerPlugin(FabricLoaderPlugin.plugin());
        }
        loader.onInit();
    }

    public static Plugin plugin() {
        String version;
        MinecraftVersion mcv = loader.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.B1_7, MinecraftVersion.B1_7_3)) {
            version = "." + MinecraftVersion.B1_7_3.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_2_1, MinecraftVersion.V1_2_5)) {
            version = "." + MinecraftVersion.V1_2_5.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_6_1, MinecraftVersion.V1_6_4)) {
            version = "." + MinecraftVersion.V1_6_4.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_7_2, MinecraftVersion.V1_7_10)) {
            version = "." + MinecraftVersion.V1_7_10.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = "." + MinecraftVersion.V1_8_8.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_13_2)) {
            version = "." + MinecraftVersion.V1_13_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_14, MinecraftVersion.V1_15_2)) {
            version = "." + MinecraftVersion.V1_15_2.getDelimiterString();
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
                "dev.neuralnexus.taterlib" + version + ".bukkit.BukkitTaterLibPlugin";
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
    public void onEnable() {
        loader.onEnable();
    }

    @Override
    public void onDisable() {
        loader.onDisable();
    }
}
