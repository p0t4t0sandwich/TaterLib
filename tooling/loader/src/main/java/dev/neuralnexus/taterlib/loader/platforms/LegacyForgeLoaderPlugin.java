package dev.neuralnexus.taterlib.loader.platforms;

import cpw.mods.fml.common.Mod;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.plugin.Plugin;

/** Legacy Forge entry point. */
@Mod(
        modid = TaterLib.Constants.PROJECT_ID,
        name = TaterLib.Constants.PROJECT_NAME,
        useMetadata = true,
        acceptableRemoteVersions = "*",
        bukkitPlugin = TaterLib.Constants.PROJECT_NAME)
public class LegacyForgeLoaderPlugin {
    private static Loader loader;

    public LegacyForgeLoaderPlugin() {
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
        if (mcv.isInRange(true, MinecraftVersion.V1_7_2, true, MinecraftVersion.V1_7_10)) {
            version = "." + MinecraftVersion.V1_7_10.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_7_10.getDelimiterString();
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
