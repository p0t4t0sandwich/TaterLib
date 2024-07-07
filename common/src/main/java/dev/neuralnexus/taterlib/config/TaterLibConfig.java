/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.config;

import dev.neuralnexus.taterlib.config.sections.HookConfig;
import dev.neuralnexus.taterlib.config.sections.MixinConfig;
import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterlib.config.sections.ServerConfig;

import java.util.List;

/** A class for TaterLib configuration. */
public interface TaterLibConfig {
    /**
     * Get the version of the configuration.
     *
     * @return The version of the configuration.
     */
    int version();

    /**
     * Get the server configuration.
     *
     * @return The server configuration.
     */
    ServerConfig server();

    /**
     * Get the modules in the configuration.
     *
     * @return The modules in the configuration.
     */
    List<ModuleConfig> modules();

    /**
     * Get the hooks in the configuration.
     *
     * @return The hooks in the configuration.
     */
    List<HookConfig> hooks();

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
        return modules().stream()
                .anyMatch(
                        moduleConfig ->
                                moduleConfig.name().equals(moduleName) && moduleConfig.enabled());
    }

    /**
     * Check if a hook is enabled in the configuration.
     *
     * @param hookName The name of the hook class.
     * @return Whether the hook should be applied.
     */
    default boolean checkHook(String hookName) {
        return hooks().stream()
                .anyMatch(hookConfig -> hookConfig.name().equals(hookName) && hookConfig.enabled());
    }
}
