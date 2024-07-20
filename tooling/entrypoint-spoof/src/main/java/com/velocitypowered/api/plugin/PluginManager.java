/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package com.velocitypowered.api.plugin;

import java.util.Collection;
import java.util.Optional;

/** Fake Velocity interface */
public interface PluginManager {
    Optional<PluginContainer> getPlugin(String id);

    Collection<PluginContainer> getPlugins();
}
