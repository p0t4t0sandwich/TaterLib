/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
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
