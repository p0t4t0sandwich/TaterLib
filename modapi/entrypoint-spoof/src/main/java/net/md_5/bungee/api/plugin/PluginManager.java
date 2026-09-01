/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.md_5.bungee.api.plugin;

import java.util.Collection;
import java.util.Collections;

/** Fake BungeeCord class. */
public class PluginManager {
    public Plugin getPlugin(String name) {
        return new Plugin() {};
    }

    public Collection<Plugin> getPlugins() {
        return Collections.emptyList();
    }
}
