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
        loader.registerPlugin(getPlugin());
        if (TaterAPIProvider.serverType().isFabricHybrid()) {
            loader.registerPlugin(BukkitLoaderPlugin.getPlugin());
        }
        loader.onInit();
    }

    public static Plugin getPlugin() {
        String version = "";
        switch (MinecraftVersion.minecraftVersion()) {
            case V1_19:
            case V1_19_1:
            case V1_19_2:
            case V1_19_3:
            case V1_19_4:
                version = "." + MinecraftVersion.V1_19.getDelimiterString();
                break;
            case V1_20:
            case V1_20_1:
            case V1_20_2:
            case V1_20_3:
            case V1_20_4:
                version = "." + MinecraftVersion.V1_20.getDelimiterString();
                break;
            default:
                System.err.println(
                        "Unsupported Minecraft version: " + MinecraftVersion.minecraftVersion() + "We'll try to load the latest version.");
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
