/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public final class BukkitLoaderPlugin extends JavaPlugin {
    public BukkitLoaderPlugin() {
        TaterLoader.onInit();
    }

    @Override
    public void onEnable() {
        TaterLoader.onEnable();
    }

    @Override
    public void onDisable() {
        TaterLoader.onDisable();
    }
}
