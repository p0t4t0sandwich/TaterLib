/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.loader.TaterPluginResolver;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitLoaderPlugin extends JavaPlugin {
    private static Loader loader;

    public BukkitLoaderPlugin() {
        TaterAPIProvider.setPrimaryPlatform(Platform.BUKKIT);
        loader = new LoaderImpl(this, Bukkit.getServer());
        loader.registerPlugin(TaterPluginResolver.bukkit(loader));
        if (loader.platform().isForgeHybrid()) {
            loader.registerPlugin(TaterPluginResolver.forge(loader));
        } else if (loader.platform().isFabricHybrid()) {
            loader.registerPlugin(TaterPluginResolver.fabric(loader));
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
