package org.spongepowered.api;

import org.spongepowered.api.plugin.PluginManager;
import org.spongepowered.plugin.PluginContainer;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/** Fake Sponge API class. */
public final class Sponge {
    public static Server getServer() {
        return new Server() {};
    }

    public static Server server() {
        return new Server() {};
    }

    public static PluginManager pluginManager() {
        return new PluginManager() {
            @Override
            public Optional<PluginContainer> plugin(String id) {
                return Optional.empty();
            }

            @Override
            public Optional<PluginContainer> getPlugin(String id) {
                return Optional.empty();
            }

            @Override
            public Collection<PluginContainer> plugins() {
                return Collections.emptyList();
            }

            @Override
            public Collection<PluginContainer> getPlugins() {
                return Collections.emptyList();
            }
        };
    }

    public static PluginManager getPluginManager() {
        return pluginManager();
    }
}
