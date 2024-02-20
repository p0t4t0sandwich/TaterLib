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
