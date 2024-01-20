package org.bukkit.plugin.java;

import java.util.logging.Logger;

/** Fake Bukkit abstract Plugin class to simplify the creation of entrypoints. */
public abstract class JavaPlugin {
    public Logger getLogger() {
        return null;
    }

    public void onEnable() {}

    public void onDisable() {}
}
