package dev.neuralnexus.taterlib.loader;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.plugin.Loader;
import dev.neuralnexus.taterlib.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

/** Loader entry point. */
public class TaterLibLoader implements Loader {
    private final Object plugin;
    private final Object pluginServer;
    private final Object pluginLogger;

    Set<Plugin> plugins = new HashSet<>();

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
    public void registerPlugin(Plugin plugin) {
        // TODO: Handle this a tad better
        if (plugin == null) {
            return;
        }
        //        if (plugins.stream().anyMatch(p -> p.getId().equals(plugin.getId()))) {
        //            throw new IllegalArgumentException(
        //                    String.format("Plugin with id %s already registered",
        // plugin.getId()));
        //        }
        plugins.add(plugin);
    }

    @Override
    public void unregisterPlugin(String pluginId) {
        if (plugins.stream().noneMatch(p -> p.id().equals(pluginId))) {
            throw new IllegalArgumentException(
                    String.format("Plugin with id %s not registered", pluginId));
        }
        plugins.removeIf(p -> p.id().equals(pluginId));
    }

    @Override
    public void onInit() {
        plugins.forEach(p -> p.platformInit(plugin, pluginServer, pluginLogger));
    }

    @Override
    public void onEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());
        plugins.forEach(Plugin::platformEnable);
    }

    @Override
    public void onDisable() {
        plugins.forEach(Plugin::platformDisable);
    }
}
