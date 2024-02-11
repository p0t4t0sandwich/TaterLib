package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
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
        loader = new TaterLibLoader(this, null);
        loader.registerPlugin(getPlugin());
        if (TaterAPIProvider.serverType().isForgeHybrid()) {
            loader.registerPlugin(BukkitLoaderPlugin.getPlugin());
        }
        // Sinytra Connector support
        if (ServerType.isFabric()) {
            loader.registerPlugin(FabricLoaderPlugin.getPlugin());
        }
        loader.onInit();
        loader.onEnable();
    }

    public static Plugin getPlugin() {
        String version = "Unsupported";
        switch (MinecraftVersion.getMinecraftVersion()) {
            case V1_19:
            case V1_19_1:
            case V1_19_2:
            case V1_19_3:
            case V1_19_4:
                version = MinecraftVersion.V1_19_4.getDelimiterString();
                break;
            case V1_20:
            case V1_20_1:
            case V1_20_2:
            case V1_20_3:
            case V1_20_4:
                version = MinecraftVersion.V1_20_2.getDelimiterString();
                break;
            default:
                System.err.println(
                        "Unsupported Minecraft version: " + MinecraftVersion.getMinecraftVersion());
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib." + version + ".forge.ForgeTaterLibPlugin";
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
