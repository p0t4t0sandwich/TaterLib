package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;

import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public class FabricLoaderPlugin implements ModInitializer {
    private final Loader loader;

    public FabricLoaderPlugin() {
        loader = new TaterLibLoader(this, null);

        String version = "Unsupported";
        switch (MinecraftVersion.getMinecraftVersion()) {
            case V1_20:
            case V1_20_1:
            case V1_20_2:
            case V1_20_3:
            case V1_20_4:
                version = MinecraftVersion.V1_20.getDelimiterString();
                break;
            default:
                System.err.println(
                        "Unsupported Minecraft version: " + MinecraftVersion.getMinecraftVersion());
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib." + version + ".fabric.FabricTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            loader.registerPlugin(
                    (dev.neuralnexus.taterlib.plugin.Plugin)
                            pluginClass.getConstructor().newInstance());
        } catch (Exception e) {
            System.err.println("Failed to load plugin class: " + pluginClassName);
            e.printStackTrace();
        }
        loader.onInit();
    }

    @Override
    public void onInitialize() {
        loader.onEnable();
    }
}
