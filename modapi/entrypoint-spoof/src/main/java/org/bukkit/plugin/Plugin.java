/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package org.bukkit.plugin;

import org.bukkit.Server;

/** Fake Bukkit Plugin interface. */
public interface Plugin {
    void onEnable();

    void onDisable();

    String getName();

    Server getServer();

    PluginDescriptionFile getDescription();
}
