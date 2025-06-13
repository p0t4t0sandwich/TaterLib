/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.platforms.TaterMetadata;
import dev.neuralnexus.taterloader.TaterPluginResolver;

import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitLoaderPlugin extends JavaPlugin {
    private static Loader loader;

    public BukkitLoaderPlugin() {
        TaterMetadata.initBukkit();
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
