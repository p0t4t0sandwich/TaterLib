package org.bukkit.plugin.java;

import org.bukkit.Server;

import java.util.logging.Logger;

/** Fake Bukkit abstract Plugin class to simplify the creation of entrypoints. */
public abstract class JavaPlugin extends PluginBase {
    public Logger getLogger() {
        return null;
    }

    public Server getServer() {
        return null;
    }

    public void onEnable() {}

    public void onDisable() {}
}
