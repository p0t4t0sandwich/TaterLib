/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.loader.impl.LoaderImpl;
import dev.neuralnexus.taterloader.TaterPluginResolver;

import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitLoaderPlugin extends JavaPlugin {
    private static Loader loader;

    public BukkitLoaderPlugin() {
        MetaAPI.instance().setPrimaryPlatform(Platforms.BUKKIT);
        loader = new LoaderImpl(this);
        loader.registerPlugin(TaterPluginResolver.bukkit());
        if (MetaAPI.instance().isPlatformPresent(Platforms.FORGE)) {
            loader.registerPlugin(TaterPluginResolver.forge());
        } else if (MetaAPI.instance().isPlatformPresent(Platforms.FABRIC)) {
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
