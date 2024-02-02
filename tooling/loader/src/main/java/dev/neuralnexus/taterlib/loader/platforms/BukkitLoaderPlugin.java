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

        String version = MinecraftVersion.getMinecraftVersion().getDelimiterString();
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
