package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;

import net.md_5.bungee.api.plugin.Plugin;

/** Bungee entry point. */
public class BungeeLoaderPlugin extends Plugin {
    private final Loader loader;

    public BungeeLoaderPlugin() {
        loader = new TaterLibLoader(this, getLogger());

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
                getLogger()
                        .severe(
                                "Unsupported Minecraft version: "
                                        + MinecraftVersion.getMinecraftVersion());
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib." + version + ".bungee.BungeeTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            loader.registerPlugin(
                    (dev.neuralnexus.taterlib.plugin.Plugin)
                            pluginClass.getConstructor().newInstance());
        } catch (Exception e) {
            getLogger().severe("Failed to load plugin class: " + pluginClassName);
            e.printStackTrace();
        }
        loader.onInit();
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
