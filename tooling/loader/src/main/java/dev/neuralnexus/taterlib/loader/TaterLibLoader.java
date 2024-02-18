package dev.neuralnexus.taterlib.loader;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;

/** Loader entry point. */
public class TaterLibLoader implements Loader {
    private final Object plugin;
    private final Object pluginServer;
    private final Object pluginLogger;
    private final Collection<Plugin> plugins = new ArrayList<>();

    public TaterLibLoader(Object plugin, Object pluginServer, Object pluginLogger) {
        this.plugin = plugin;
        this.pluginServer = pluginServer;
        this.pluginLogger = pluginLogger;
        TaterAPIProvider.register();
    }

    @Override
    public Object plugin() {
        return plugin;
    }

    @Override
    public Object server() {
        return pluginServer;
    }

    @Override
    public Object logger() {
        return pluginLogger;
    }

    @Override
    public Collection<Plugin> plugins() {
        return plugins;
    }
}
