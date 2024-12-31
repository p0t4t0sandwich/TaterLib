/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api;

import dev.neuralnexus.modapi.crossperms.api.impl.PermsAPIImpl;

/** Permissions API */
public interface PermsAPI {
    default PermsAPI instance() {
        return PermsAPIImpl.getInstance();
    }

    /** Get all providers */
    Iterable<PermissionsProvider> providers();

    /**
     * Register a provider
     *
     * @param provider The provider to register
     */
    void registerProvider(PermissionsProvider provider);

    /**
     * Unregister a provider
     *
     * @param name The name of the provider
     */
    void unregisterProvider(String name);

    /**
     * Unregister a provider
     *
     * @param provider The provider to unregister
     */
    void unregisterProvider(PermissionsProvider provider);

    /**
     * Check a source's permission
     *
     * @param source The source to check
     * @param permissionLevel The permission level
     */
    default boolean hasPermission(Object source, int permissionLevel) {
        for (PermissionsProvider provider : providers()) {
            if (provider.hasPermission(source, permissionLevel)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check a source's permission
     *
     * @param source The source to check
     * @param permission The permission
     */
    default boolean hasPermission(Object source, String permission) {
        for (PermissionsProvider provider : providers()) {
            if (provider.hasPermission(source, permission)) {
                return true;
            }
        }
        return false;
    }
}
