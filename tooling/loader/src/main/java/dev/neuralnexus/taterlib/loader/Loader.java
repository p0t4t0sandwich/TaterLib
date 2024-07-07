/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader;

import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.event.api.PluginEvents;
import dev.neuralnexus.taterapi.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.metadata.PlatformData;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;
import dev.neuralnexus.taterlib.plugin.ModuleLoader;
import dev.neuralnexus.taterlib.plugin.Plugin;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterlib.plugin.impl.ModuleLoaderImpl;

import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Loader {
    static Loader instance() {
        Loader ret = LoaderImpl.getInstance();
        if (ret == null) {
            throw new IllegalStateException("TaterLib Loader instance not initialized");
        }
        return ret;
    }

    /** Get the platform data. */
    PlatformData platformData();

    /** Get the platform */
    default Platform platform() {
        return Platform.get();
    }

    /** Get the platform's Minecraft version. */
    default MinecraftVersion minecraftVersion() {
        return platformData().minecraftVersion();
    }

    /**
     * Get the platform's logger.
     *
     * @param name The name of the logger
     * @return The logger
     */
    default Logger logger(String name) {
        return platformData().logger(name);
    }

    /** Get the platform's server/plugin/container. */
    @ApiStatus.Internal
    Object plugin();

    /** Get the platform's server. */
    @ApiStatus.Internal
    Object server();

    /** Get the platform's other data. */
    @ApiStatus.Internal
    Object[] other();

    /** Get the collection of plugins. */
    @ApiStatus.Internal
    List<Plugin> plugins();

    /** Get the plugin modules. */
    @ApiStatus.Internal
    Map<String, ModuleLoader> pluginModules();

    /** Register a plugin. */
    @ApiStatus.Internal
    default void registerPlugin(Plugin plugin) {
        if (plugin == null) {
            return;
        }
        plugins().add(plugin);
    }

    /** Unregister a plugin. */
    @ApiStatus.Internal
    default void unregisterPlugin(String pluginId) {
        if (plugins().stream().noneMatch(p -> p.id().equals(pluginId))) {
            throw new IllegalArgumentException(
                    String.format("Plugin with id %s not registered", pluginId));
        }
        plugins().removeIf(p -> p.id().equals(pluginId));
    }

    /** Unregister a plugin. */
    @ApiStatus.Internal
    default void unregisterPlugin(Plugin plugin) {
        unregisterPlugin(plugin.id());
    }

    /**
     * Get plugin module loader by plugin id.
     *
     * @param pluginId The plugin id
     * @return The module loader
     */
    default Optional<ModuleLoader> pluginModuleLoader(String pluginId) {
        return Optional.ofNullable(pluginModules().get(pluginId));
    }

    /** Register a plugin module */
    default void registerPluginModule(String pluginId, PluginModule module) {
        if (!pluginModules().containsKey(pluginId)) {
            pluginModules().put(pluginId, new ModuleLoaderImpl());
        }
        pluginModules().get(pluginId).registerModule(module);
    }

    /** Register a plugin module */
    default void registerPluginModule(Plugin plugin, PluginModule module) {
        registerPluginModule(plugin.id(), module);
    }

    /** Unregister a plugin module */
    default void unregisterPluginModule(String pluginId, String moduleId) {
        if (pluginModules().containsKey(pluginId)) {
            pluginModules().get(pluginId).unregisterModule(moduleId);
        }
    }

    /** Unregister a plugin module */
    default void unregisterPluginModule(Plugin plugin, String moduleId) {
        unregisterPluginModule(plugin.id(), moduleId);
    }

    /** Unregister a plugin module */
    default void unregisterPluginModule(String pluginId, PluginModule module) {
        unregisterPluginModule(pluginId, module.id());
    }

    /** Unregister a plugin module */
    default void unregisterPluginModule(Plugin plugin, PluginModule module) {
        unregisterPluginModule(plugin.id(), module.id());
    }

    /** Run Init on all plugins. */
    @ApiStatus.Internal
    default void onInit() {
        plugins().forEach(Plugin::onInit);
    }

    /** Run Enable on all plugins. */
    @ApiStatus.Internal
    default void onEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());
        plugins()
                .forEach(
                        plugin -> {
                            try {
                                plugin.onEnable();
                                if (pluginModules().containsKey(plugin.id())) {
                                    ModuleLoader moduleLoader = pluginModules().get(plugin.id());
                                    Loader.instance()
                                            .logger(plugin.id())
                                            .info(
                                                    "Starting modules: "
                                                            + moduleLoader.moduleNames());
                                    moduleLoader.onEnable();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
    }

    /** Run Disable on all plugins. */
    @ApiStatus.Internal
    default void onDisable() {
        plugins()
                .forEach(
                        plugin -> {
                            try {
                                if (pluginModules().containsKey(plugin.id())) {
                                    ModuleLoader moduleLoader = pluginModules().get(plugin.id());
                                    Loader.instance()
                                            .logger(plugin.id())
                                            .info(
                                                    "Stopping modules: "
                                                            + moduleLoader.moduleNames());
                                    moduleLoader.onDisable();
                                }
                                plugin.onDisable();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
    }
}
