package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.plugin.Plugin;

import net.minecraftforge.fml.common.Mod;

/** Forge entry point. */
@Mod(
        value = TaterLib.Constants.PROJECT_ID,
        modid = TaterLib.Constants.PROJECT_ID,
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions = "*")
public class ForgeLoaderPlugin {
    private static Loader loader;

    public ForgeLoaderPlugin() {
        loader = new TaterLibLoader(this, null, null);
        loader.registerPlugin(plugin());
        // if (TaterAPIProvider.serverType().isForgeHybrid()) {
        //     loader.registerPlugin(BukkitLoaderPlugin.getPlugin());
        // }
        // Sinytra Connector support
        if (ServerType.isFabric()) {
            loader.registerPlugin(FabricLoaderPlugin.plugin());
        }
        loader.onInit();
        loader.onEnable();
    }

    public static Plugin plugin() {
        String version;
        MinecraftVersion mcv = MinecraftVersion.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_8, MinecraftVersion.V1_8_9)) {
            version = "." + MinecraftVersion.V1_8_9.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_9, MinecraftVersion.V1_9_4)) {
            version = "." + MinecraftVersion.V1_9_4.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_10, MinecraftVersion.V1_10_2)) {
            version = "." + MinecraftVersion.V1_10_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_11, MinecraftVersion.V1_11_2)) {
            version = "." + MinecraftVersion.V1_11_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_12, MinecraftVersion.V1_12_2)) {
            version = "." + MinecraftVersion.V1_12_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_13, MinecraftVersion.V1_13_2)) {
            version = "." + MinecraftVersion.V1_13_2.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_14, MinecraftVersion.V1_14_4)) {
            version = "." + MinecraftVersion.V1_14_4.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_15, MinecraftVersion.V1_15_2)) {
            version = "." + MinecraftVersion.V1_15_1.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_16, MinecraftVersion.V1_16_5)) {
            version = "." + MinecraftVersion.V1_16_3.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_17, MinecraftVersion.V1_17_1)) {
            version = "." + MinecraftVersion.V1_17_1.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_18, MinecraftVersion.V1_18_2)) {
            version = "." + MinecraftVersion.V1_18.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_19, MinecraftVersion.V1_19_4)) {
            version = "." + MinecraftVersion.V1_19.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_20_4)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_20_5, MinecraftVersion.V1_20_6)) {
            version = "." + MinecraftVersion.V1_20_6.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_20_2.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".forge.ForgeTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            return (Plugin) pluginClass.getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to load plugin class: " + pluginClassName);
            e.printStackTrace();
        }
        return null;
    }
}
