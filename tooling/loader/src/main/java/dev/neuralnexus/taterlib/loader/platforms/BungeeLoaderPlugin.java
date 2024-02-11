package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;

import net.md_5.bungee.api.plugin.Plugin;

/** Bungee entry point. */
public class BungeeLoaderPlugin extends Plugin {
    private static Loader loader;

    public BungeeLoaderPlugin() {
        loader = new TaterLibLoader(this, getLogger());
        loader.registerPlugin(getPlugin());
        loader.onInit();
    }

    public static dev.neuralnexus.taterlib.plugin.Plugin getPlugin() {
        String version = "";
        switch (MinecraftVersion.getMinecraftVersion()) {
            case V1_20:
            case V1_20_1:
            case V1_20_2:
            case V1_20_3:
            case V1_20_4:
                version = "." + MinecraftVersion.V1_20.getDelimiterString();
                break;
            default:
                System.err.println(
                        "Unsupported Minecraft version: " + MinecraftVersion.getMinecraftVersion());
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
