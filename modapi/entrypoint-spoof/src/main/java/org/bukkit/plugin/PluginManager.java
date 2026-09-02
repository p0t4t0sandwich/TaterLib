/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package org.bukkit.plugin;

import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Fake Bukkit interface */
public interface PluginManager {
    void registerEvents(Listener listener, Plugin plugin);

    @Nullable Plugin getPlugin(@NotNull String name);

    Plugin[] getPlugins();
}
