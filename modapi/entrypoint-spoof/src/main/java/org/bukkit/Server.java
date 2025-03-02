/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package org.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

/** Fake Bukkit Server interface. */
public interface Server {
    Logger getLogger();

    /**
     * Gets the name of this server implementation.
     *
     * @return name of this server implementation
     */
    @NotNull String getName();

    /**
     * Gets the version string of this server implementation.
     *
     * @return version of this server implementation
     */
    @NotNull String getVersion();

    /**
     * Gets the Bukkit version that this server is running.
     *
     * @return version of Bukkit
     */
    @NotNull String getBukkitVersion();

    // Paper start - expose game version
    /**
     * Gets the version of game this server implements
     *
     * @return version of game
     */
    @NotNull String getMinecraftVersion();

    // Paper end

    Player[] getOnlinePlayers();

    PluginManager getPluginManager();
}
