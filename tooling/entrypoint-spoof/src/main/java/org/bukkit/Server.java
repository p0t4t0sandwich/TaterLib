package org.bukkit;

import org.bukkit.entity.Player;

import java.util.logging.Logger;

/** Fake Bukkit Server interface. */
public interface Server {
    Logger getLogger();

    String getName();

    Player[] getOnlinePlayers();
}
