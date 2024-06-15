package org.spongepowered.api.plugin;

import org.spongepowered.plugin.PluginContainer;

import java.util.Collection;
import java.util.Optional;

/**
 * Fake Sponge interface
 */
public interface PluginManager {
    Optional<PluginContainer> plugin(String id);

    Optional<PluginContainer> getPlugin(String id);

    Collection<PluginContainer> plugins();

    Collection<PluginContainer> getPlugins();
}
