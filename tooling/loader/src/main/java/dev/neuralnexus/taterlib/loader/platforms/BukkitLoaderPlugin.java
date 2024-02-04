package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.plugin.Plugin;

import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitLoaderPlugin extends JavaPlugin {
    private final Loader loader;

    public BukkitLoaderPlugin() {
        loader = new TaterLibLoader(this, getLogger());

        String version = "Unsupported";
        switch (MinecraftVersion.getMinecraftVersion()) {
            case V1_20:
            case V1_20_1:
                version = MinecraftVersion.V1_20.getDelimiterString();
                break;
            case V1_20_2:
                version = MinecraftVersion.V1_20_2.getDelimiterString();
                break;
            case V1_20_3:
            case V1_20_4:
                version = MinecraftVersion.V1_20_4.getDelimiterString();
                break;
            default:
                getLogger()
                        .severe(
                                "Unsupported Minecraft version: "
                                        + MinecraftVersion.getMinecraftVersion());
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib." + version + ".bukkit.BukkitTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            loader.registerPlugin((Plugin) pluginClass.getConstructor().newInstance());
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
