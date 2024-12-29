/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api;

import dev.neuralnexus.modapi.crossperms.api.impl.PermsAPIImpl;

import java.util.Set;

/** Permissions API */
public interface PermsAPI {
    default PermsAPI instance() {
        return PermsAPIImpl.getInstance();
    }

    /** Get all hooks */
    Set<PermissionsHook> hooks();

    /**
     * Register a hook
     *
     * @param hook The hook to register
     */
    default void registerHook(PermissionsHook hook) {
        hooks().add(hook);
    }

    /**
     * Unregister a hook
     *
     * @param name The name of the hook
     */
    default void unregisterHook(String name) {
        hooks().removeIf(hook -> hook.name().equalsIgnoreCase(name));
    }

    /**
     * Unregister a hook
     *
     * @param hook The hook to unregister
     */
    default void unregisterHook(PermissionsHook hook) {
        hooks().remove(hook);
    }

    /**
     * Check a source's permission
     *
     * @param source The source to check
     * @param permission The permission
     */
    default boolean hasPermission(Object source, String permission) {
        return hooks().stream().anyMatch(hook -> hook.hasPermission(source, permission));
    }
}
