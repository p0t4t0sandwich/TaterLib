/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package org.bukkit.util.config;

/** Fake Bukkit ConfigurationNode class. */
public class ConfigurationNode {
    public Object getProperty(String path) {
        return null;
    }

    public void setProperty(String path, Object value) {}

    public boolean getBoolean(String path, boolean def) {
        return false;
    }

    public String getString(String path) {
        return "";
    }
}
