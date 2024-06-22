/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader;

import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.Platform;
import dev.neuralnexus.taterlib.loader.api.PlatformData;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;
import dev.neuralnexus.taterlib.plugin.Plugin;

import java.util.List;

public interface Loader {
    default Loader instance() {
        Loader ret = LoaderImpl.getInstance();
        if (ret == null) {
            throw new IllegalStateException("Loader instance not initialized");
        }
        return ret;
    }

    /** Get the platform data. */
    PlatformData platformData();

    /** Get the platform */
    default Platform platform() {
        return platformData().platform();
    }

    /** Get the platform's Minecraft version. */
    default MinecraftVersion minecraftVersion() {
        return platformData().minecraftVersion();
    }

    /** Get the platform's server/plugin/container. */
    Object plugin();

    /** Get the platform's server. */
    Object server();

    /** Get the platform's logger. */
    Object logger();

    /** Get the collection of plugins. */
    List<Plugin> plugins();

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
        plugins()
                .forEach(
                        plugin -> {
                            try {
                                plugin.platformEnable();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
    }

    /** Run Disable on all plugins. */
    default void onDisable() {
        plugins().forEach(Plugin::platformDisable);
    }
}
