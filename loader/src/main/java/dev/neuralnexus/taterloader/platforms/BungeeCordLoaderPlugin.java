/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterloader.platforms;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;

import net.md_5.bungee.api.plugin.Plugin;

/** Bungee entry point. */
public final class BungeeCordLoaderPlugin extends Plugin {
    public BungeeCordLoaderPlugin() {
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
