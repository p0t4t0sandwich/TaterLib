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
        loader.registerPlugin(plugin());
        if (TaterAPIProvider.serverType().isForgeHybrid()) {
            loader.registerPlugin(ForgeLoaderPlugin.plugin());
        } else if (TaterAPIProvider.serverType().isFabricHybrid()) {
            loader.registerPlugin(FabricLoaderPlugin.plugin());
        }
        loader.onInit();
    }

    public static Plugin plugin() {
        String version;
        MinecraftVersion mcv = MinecraftVersion.minecraftVersion();
        if (mcv.isInRange(true, MinecraftVersion.B1_7, true, MinecraftVersion.B1_7_3)) {
            version = "." + MinecraftVersion.B1_7_3.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_2_1, true, MinecraftVersion.V1_2_5)) {
            version = "." + MinecraftVersion.V1_2_5.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_6_1, true, MinecraftVersion.V1_6_4)) {
            version = "." + MinecraftVersion.V1_6_4.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_7_2, true, MinecraftVersion.V1_7_10)) {
            version = "." + MinecraftVersion.V1_7_10.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_8, true, MinecraftVersion.V1_8_9)) {
            version = "." + MinecraftVersion.V1_8_8.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_9, true, MinecraftVersion.V1_13_2)) {
            version = "." + MinecraftVersion.V1_13_2.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_14, true, MinecraftVersion.V1_15_2)) {
            version = "." + MinecraftVersion.V1_15_2.getDelimiterString();
        } else if (mcv.isInRange(true, MinecraftVersion.V1_16, true, MinecraftVersion.V1_20_6)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
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
