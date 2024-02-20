package org.bukkit;

import java.util.logging.Logger;

/** Fake Bukkit Server interface. */
public interface Server {
    Logger getLogger();

    String getName();
}
