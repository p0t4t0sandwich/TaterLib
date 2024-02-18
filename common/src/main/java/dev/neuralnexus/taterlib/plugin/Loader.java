package dev.neuralnexus.taterlib.plugin;

import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;

import java.util.Collection;

public interface Loader {
    /** Get the platform's server/plugin/container. */
    Object plugin();

    /** Get the platform's server. */
    Object server();

    /** Get the platform's logger. */
    Object logger();

    /** Get the collection of plugins. */
    Collection<Plugin> plugins();

    /** Register a plugin. */
    default void registerPlugin(Plugin plugin) {
        if (plugin == null) {
            return;
        }
        plugins().add(plugin);
    }

    /** Unregister a plugin. */
    default void unregisterPlugin(String pluginId) {
        if (plugins().stream().noneMatch(p -> p.id().equals(pluginId))) {
            throw new IllegalArgumentException(
                    String.format("Plugin with id %s not registered", pluginId));
        }
        plugins().removeIf(p -> p.id().equals(pluginId));
    }

    /** Unregister a plugin. */
    default void unregisterPlugin(Plugin plugin) {
        unregisterPlugin(plugin.id());
    }

    /** Run Init on all plugins. */
    default void onInit() {
        plugins().forEach(p -> p.platformInit(plugin(), plugin(), logger()));
    }

    /** Run Enable on all plugins. */
    default void onEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());
        plugins().forEach(Plugin::platformEnable);
    }

    /** Run Disable on all plugins. */
    default void onDisable() {
        plugins().forEach(Plugin::platformDisable);
    }
}
