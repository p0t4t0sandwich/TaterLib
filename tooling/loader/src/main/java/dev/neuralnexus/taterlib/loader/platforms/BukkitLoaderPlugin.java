package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.v1_20.bukkit.BukkitTaterLibPlugin;

import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitLoaderPlugin extends JavaPlugin {
    private final Loader loader;

    public BukkitLoaderPlugin() {
        loader = new TaterLibLoader(this, getLogger());
        loader.registerPlugin(new BukkitTaterLibPlugin());
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
