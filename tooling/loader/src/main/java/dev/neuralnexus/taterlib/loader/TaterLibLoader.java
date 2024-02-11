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
    private final Object logger;
    private final Object plugin;
    Set<Plugin> plugins = new HashSet<>();

    public TaterLibLoader(Object plugin, Object logger) {
        this.plugin = plugin;
        this.logger = logger;
        TaterAPIProvider.register();
    }

    @Override
    public Object getLogger() {
        return logger;
    }

    @Override
    public Object getPlugin() {
        return plugin;
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
        if (plugins.stream().noneMatch(p -> p.getId().equals(pluginId))) {
            throw new IllegalArgumentException(
                    String.format("Plugin with id %s not registered", pluginId));
        }
        plugins.removeIf(p -> p.getId().equals(pluginId));
    }

    @Override
    public void onInit() {
        plugins.forEach(p -> p.platformInit(plugin, logger));
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
