/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package org.spongepowered.api.plugin;

import java.util.Collection;
import java.util.Optional;

/** Fake Sponge interface */
public interface PluginManager {
    Optional<org.spongepowered.plugin.PluginContainer> plugin(String id);

    Optional<org.spongepowered.api.plugin.PluginContainer> getPlugin(String id);

    Collection<org.spongepowered.plugin.PluginContainer> plugins();

    Collection<org.spongepowered.api.plugin.PluginContainer> getPlugins();
}
