/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.platforms;

import dev.neuralnexus.taterlib.testmod.TestMod;
import dev.neuralnexus.taterloader.event.api.PluginEvents;

import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitPlugin extends JavaPlugin {
    public BukkitPlugin() {
        PluginEvents.ENABLED.register(event -> TestMod.instance().onEnable());
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}
}
