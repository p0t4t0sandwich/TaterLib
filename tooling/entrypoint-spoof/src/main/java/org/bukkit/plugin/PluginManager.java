package org.bukkit.plugin;

import org.bukkit.event.Listener;

/** Fake Bukkit interface */
public interface PluginManager {
    void registerEvents(Listener listener, Plugin plugin);
}
