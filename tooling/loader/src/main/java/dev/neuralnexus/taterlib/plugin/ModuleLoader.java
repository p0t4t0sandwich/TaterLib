/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.plugin;

import java.util.List;
import java.util.stream.Collectors;

/** PluginModule loader. */
public interface ModuleLoader {
    /** Get the modules. */
    List<PluginModule> modules();

    /** Get list of module names. */
    default List<String> moduleNames() {
        return modules().stream().map(PluginModule::name).collect(Collectors.toList());
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
        modules().removeIf(module -> module.name().equals(moduleName));
    }

    /** Start the modules. */
    default void start() {
        modules().forEach(PluginModule::start);
    }

    /** Stop the modules. */
    default void stop() {
        modules().forEach(PluginModule::stop);
    }
}
