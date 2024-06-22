package com.velocitypowered.api.plugin;

import java.util.Collection;
import java.util.Optional;

/** Fake Velocity interface */
public interface PluginManager {
    Optional<PluginContainer> getPlugin(String id);

    Collection<PluginContainer> getPlugins();
}
