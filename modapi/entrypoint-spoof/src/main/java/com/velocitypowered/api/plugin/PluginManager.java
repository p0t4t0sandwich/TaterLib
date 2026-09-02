/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package com.velocitypowered.api.plugin;

import java.util.Collection;
import java.util.Optional;

/** Fake Velocity interface */
public interface PluginManager {
    Optional<PluginContainer> getPlugin(String id);

    Collection<PluginContainer> getPlugins();

    boolean isLoaded(String id);
}
