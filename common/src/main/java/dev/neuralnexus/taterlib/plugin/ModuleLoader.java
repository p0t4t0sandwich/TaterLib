package dev.neuralnexus.taterlib.plugin;

import java.util.Set;

/** PluginModule loader. */
public interface ModuleLoader {
    /** Get the modules. */
    Set<PluginModule> modules();

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
    default void startModules() {
        modules().forEach(PluginModule::start);
    }

    /** Stop the modules. */
    default void stopModules() {
        modules().forEach(PluginModule::stop);
    }

    /** Reload the modules. */
    default void reloadModules() {
        modules().forEach(PluginModule::reload);
    }
}