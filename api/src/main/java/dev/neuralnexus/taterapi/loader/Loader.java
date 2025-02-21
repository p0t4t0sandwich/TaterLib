/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.loader;

import dev.neuralnexus.taterapi.event.api.PluginEvents;
import dev.neuralnexus.taterapi.event.plugin.PluginDisableEvent;
import dev.neuralnexus.taterapi.event.plugin.PluginEnableEvent;
import dev.neuralnexus.taterapi.loader.impl.LoaderImpl;
import dev.neuralnexus.taterapi.loader.plugin.ModuleLoader;
import dev.neuralnexus.taterapi.loader.plugin.Plugin;
import dev.neuralnexus.taterapi.loader.plugin.PluginModule;
import dev.neuralnexus.taterapi.loader.plugin.impl.ModuleLoaderImpl;
import dev.neuralnexus.taterapi.logger.Logger;

import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unused")
public interface Loader {
    static Loader instance() {
        Loader ret = LoaderImpl.getInstance();
        if (null == ret) {
            throw new IllegalStateException("TaterLib Loader instance not initialized");
        }
        return ret;
    }

    Logger logger = Logger.create("TaterLoader");

    /** Get the platform's server/plugin/container. */
    @ApiStatus.Experimental
    Object plugin();

    /** Get the collection of plugins. */
    @ApiStatus.Internal
    List<Plugin> plugins();

    /** Get the plugin modules. */
    @ApiStatus.Internal
    Map<String, ModuleLoader> pluginModules();

    /** Register a plugin. */
    @ApiStatus.Internal
    default void registerPlugin(Plugin plugin) {
        if (null == plugin) {
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
        plugins()
                .forEach(
                        plugin -> {
                            try {
                                plugin.onEnable();
                                if (pluginModules().containsKey(plugin.id())) {
                                    ModuleLoader moduleLoader = pluginModules().get(plugin.id());
                                    Logger.create(plugin.id())
                                            .info(
                                                    "Starting modules: "
                                                            + moduleLoader.moduleNames());
                                    moduleLoader.onEnable();
                                }
                            } catch (Exception e) {
                                logger.error("Plugin " + plugin.id() + " failed to enable", e);
                            }
                        });
        // Runs last so TaterLib is loaded before any dependant mod tries to hook-in
        PluginEvents.ENABLED.invoke(new PluginEnableEvent() {});
    }

    /** Run Disable on all plugins. */
    @ApiStatus.Internal
    default void onDisable() {
        // Runs first so dependant mods unload before TaterLib
        PluginEvents.DISABLED.invoke(new PluginDisableEvent() {});
        plugins()
                .forEach(
                        plugin -> {
                            try {
                                if (pluginModules().containsKey(plugin.id())) {
                                    ModuleLoader moduleLoader = pluginModules().get(plugin.id());
                                    Logger.create(plugin.id())
                                            .info(
                                                    "Stopping modules: "
                                                            + moduleLoader.moduleNames());
                                    moduleLoader.onDisable();
                                }
                                plugin.onDisable();
                            } catch (Exception e) {
                                logger.error("Plugin " + plugin.id() + " failed to disable", e);
                            }
                        });
    }
}
