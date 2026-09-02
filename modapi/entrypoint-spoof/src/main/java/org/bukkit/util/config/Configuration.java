/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
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
