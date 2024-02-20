package org.bukkit.plugin;

import org.bukkit.Server;

/** Fake Bukkit Plugin interface. */
public interface Plugin {
    Server getServer();

    PluginDescriptionFile getDescription();
}
