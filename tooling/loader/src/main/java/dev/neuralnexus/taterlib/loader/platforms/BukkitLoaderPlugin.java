package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.plugin.Plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitLoaderPlugin extends JavaPlugin {
    private static Loader loader;

    public BukkitLoaderPlugin() {
        loader = new TaterLibLoader(this, Bukkit.getServer(), Bukkit.getLogger());
        loader.registerPlugin(getPlugin());
        if (TaterAPIProvider.serverType().isForgeHybrid()) {
            loader.registerPlugin(ForgeLoaderPlugin.getPlugin());
        } else if (TaterAPIProvider.serverType().isFabricHybrid()) {
            loader.registerPlugin(FabricLoaderPlugin.getPlugin());
        }
        loader.onInit();
    }

    public static Plugin getPlugin() {
        String version = "";
        switch (MinecraftVersion.minecraftVersion()) {
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
