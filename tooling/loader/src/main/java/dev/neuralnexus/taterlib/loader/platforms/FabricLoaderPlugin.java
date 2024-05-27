package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.plugin.Plugin;

import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public class FabricLoaderPlugin implements ModInitializer {
    private static Loader loader;

    public FabricLoaderPlugin() {
        loader = new TaterLibLoader(this, null, null);
        loader.registerPlugin(plugin());
        if (TaterAPIProvider.serverType().isFabricHybrid()) {
            loader.registerPlugin(BukkitLoaderPlugin.plugin());
        }
        loader.onInit();
    }

    public static Plugin plugin() {
        String version;
        MinecraftVersion mcv = MinecraftVersion.minecraftVersion();
        if (mcv.isInRange(true, MinecraftVersion.V1_7_2, true, MinecraftVersion.V1_7_10)) {
            version = "." + MinecraftVersion.V1_7_10.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_8, true, MinecraftVersion.V1_8_9)) {
            version = "." + MinecraftVersion.V1_8_9.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_9, true, MinecraftVersion.V1_9_4)) {
            version = "." + MinecraftVersion.V1_9_4.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_10, true, MinecraftVersion.V1_10_2)) {
            version = "." + MinecraftVersion.V1_10_2.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_11, true, MinecraftVersion.V1_11_2)) {
            version = "." + MinecraftVersion.V1_11_2.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_12, true, MinecraftVersion.V1_12_2)) {
            version = "." + MinecraftVersion.V1_12_2.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_13, true, MinecraftVersion.V1_14_4)) {
            version = "." + MinecraftVersion.V1_14.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_15, true, MinecraftVersion.V1_15_2)) {
            version = "." + MinecraftVersion.V1_15.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_16, true, MinecraftVersion.V1_16_5)) {
            version = "." + MinecraftVersion.V1_16.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_17, true, MinecraftVersion.V1_17_1)) {
            version = "." + MinecraftVersion.V1_17.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_18, true, MinecraftVersion.V1_18_2)) {
            version = "." + MinecraftVersion.V1_18.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_19, true, MinecraftVersion.V1_19_4)) {
            version = "." + MinecraftVersion.V1_19.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_20, true, MinecraftVersion.V1_20_6)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
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
