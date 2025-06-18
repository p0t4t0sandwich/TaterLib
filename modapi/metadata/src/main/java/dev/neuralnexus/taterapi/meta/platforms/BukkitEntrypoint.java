/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.platforms;

import org.bukkit.plugin.java.JavaPlugin;

public class BukkitEntrypoint extends JavaPlugin {
    public BukkitEntrypoint() {
        TaterMetadata.initBukkit();
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}
}
