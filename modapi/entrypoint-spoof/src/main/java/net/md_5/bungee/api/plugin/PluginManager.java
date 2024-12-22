/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package net.md_5.bungee.api.plugin;

import java.util.Collection;
import java.util.Collections;

/** Fake BungeeCord class. */
public class PluginManager {
    public Collection<Plugin> getPlugins() {
        return Collections.emptyList();
    }
}
