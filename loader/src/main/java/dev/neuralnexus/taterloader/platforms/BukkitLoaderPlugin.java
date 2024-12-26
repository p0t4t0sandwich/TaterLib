/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterloader.Loader;
import dev.neuralnexus.taterloader.TaterPluginResolver;
import dev.neuralnexus.taterloader.impl.LoaderImpl;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitLoaderPlugin extends JavaPlugin {
    private static Loader loader;

    public BukkitLoaderPlugin() {
        TaterAPIProvider.setPrimaryPlatform(Platform.BUKKIT);
        loader = new LoaderImpl(this, Bukkit.getServer());
        loader.registerPlugin(TaterPluginResolver.bukkit());
        if (loader.platform().isForgeHybrid()) {
            loader.registerPlugin(TaterPluginResolver.forge());
        } else if (loader.platform().isFabricHybrid()) {
            loader.registerPlugin(TaterPluginResolver.fabric());
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
