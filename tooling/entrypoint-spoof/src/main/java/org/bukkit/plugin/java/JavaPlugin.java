package org.bukkit.plugin.java;

import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.util.logging.Logger;

/** Fake Bukkit abstract Plugin class to simplify the creation of entrypoints. */
public abstract class JavaPlugin extends PluginBase {
    public void onEnable() {}

    public void onDisable() {}

    public Logger getLogger() {
        return null;
    }

    public Server getServer() {
        return null;
    }

    public File getDataFolder() {
        return null;
    }

    public final boolean isEnabled() {
        return false;
    }

    public PluginDescriptionFile getDescription() {
        return null;
    }
}
