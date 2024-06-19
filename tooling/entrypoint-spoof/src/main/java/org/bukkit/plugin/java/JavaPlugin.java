package org.bukkit.plugin.java;

import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.util.logging.Logger;

/** Fake Bukkit abstract Plugin class to simplify the creation of entrypoints. */
public abstract class JavaPlugin extends PluginBase {
    Logger logger;
    Server server;
    File dataFolder;
    PluginDescriptionFile description;

    public String getName() {
        return "";
    }

    public Logger getLogger() {
        return logger;
    }

    public Server getServer() {
        return server;
    }

    public File getDataFolder() {
        return dataFolder;
    }

    public final boolean isEnabled() {
        return false;
    }

    public PluginDescriptionFile getDescription() {
        return description;
    }
}
