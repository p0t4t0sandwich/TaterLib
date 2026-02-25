/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterloader.TaterLoader;

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
