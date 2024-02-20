package org.bukkit.plugin;

import org.bukkit.Server;

/** Fake Bukkit Plugin interface. */
public interface Plugin {
    void onEnable();

    void onDisable();

    Server getServer();

    PluginDescriptionFile getDescription();
}
