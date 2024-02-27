package org.bukkit.util.config;

import java.io.File;

/** Fake Bukkit Configuration class. */
public class Configuration extends ConfigurationNode {
    public Configuration(File file) {}

    public void load() {}

    public void setHeader(String... headerLines) {}

    public boolean save() {
        return false;
    }
}
