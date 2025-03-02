/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.config;

import dev.neuralnexus.taterapi.config.MixinConfig;

import java.util.Map;

/** A class for TaterLib configuration. */
public interface TaterLibConfig {
    /**
     * Get the version of the configuration.
     *
     * @return The version of the configuration.
     */
    int version();

    /**
     * Get the modules in the configuration.
     *
     * @return The modules in the configuration.
     */
    Map<String, Boolean> modules();

    /**
     * Get the hooks in the configuration.
     *
     * @return The hooks in the configuration.
     */
    Map<String, Boolean> hooks();

    /**
     * Get the mixins in the configuration.
     *
     * @return The mixins in the configuration.
     */
    MixinConfig mixin();

    /**
     * Check if a module is enabled in the configuration.
     *
     * @param moduleName The name of the module.
     * @return Whether the module should be applied.
     */
    default boolean checkModule(String moduleName) {
        return modules().getOrDefault(moduleName, false);
    }

    /**
     * Check if a hook is enabled in the configuration.
     *
     * @param hookName The name of the hook class.
     * @return Whether the hook should be applied.
     */
    default boolean checkHook(String hookName) {
        return hooks().getOrDefault(hookName, false);
    }
}
