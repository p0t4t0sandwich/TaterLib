/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.loader.plugin;

import java.util.List;
import java.util.stream.Collectors;

/** PluginModule loader. */
public interface ModuleLoader {
    /** Get the modules. */
    List<PluginModule> modules();

    /** Get list of module names. */
    default List<String> moduleNames() {
        return modules().stream().map(PluginModule::id).collect(Collectors.toList());
    }

    /**
     * Register a module.
     *
     * @param module The module.
     */
    default void registerModule(PluginModule module) {
        modules().add(module);
    }

    /**
     * Unregister a module.
     *
     * @param moduleName The module.
     */
    default void unregisterModule(String moduleName) {
        modules().removeIf(module -> module.id().equals(moduleName));
    }

    /** Start the modules. */
    default void onEnable() {
        modules().forEach(PluginModule::onEnable);
    }

    /** Stop the modules. */
    default void onDisable() {
        modules().forEach(PluginModule::onDisable);
    }
}
