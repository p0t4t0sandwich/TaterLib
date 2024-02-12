package dev.neuralnexus.taterlib.config;

import dev.neuralnexus.taterlib.config.sections.HookConfig;
import dev.neuralnexus.taterlib.config.sections.MixinConfig;
import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterlib.config.sections.ServerConfig;

import java.util.Set;

/** A class for TaterLib configuration. */
public interface Config {
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
    Set<ModuleConfig> modules();

    /**
     * Get the hooks in the configuration.
     *
     * @return The hooks in the configuration.
     */
    Set<HookConfig> hooks();

    /**
     * Get the mixins in the configuration.
     *
     * @return The mixins in the configuration.
     */
    Set<MixinConfig> mixins();

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

    /**
     * Check if a mixin is enabled in the configuration.
     *
     * @param mixinClassName The name of the mixin class.
     * @return Whether the mixin should be applied.
     */
    default boolean checkMixin(String mixinClassName) {
        return mixins().stream()
                .filter(mixinConfig -> mixinClassName.contains(mixinConfig.name()))
                .allMatch(MixinConfig::checkMixin);
    }
}
