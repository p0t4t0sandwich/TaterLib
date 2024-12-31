/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api;

import org.jetbrains.annotations.NotNull;

/** A generic provider for permissions plugins */
public interface PermissionsProvider {
    /**
     * Get the name of the provider
     *
     * @return The name of the provider
     */
    String name();

    /**
     * Get if a subject has a permission
     *
     * @param subject The subject to check
     * @param permissionLevel The permission level to check
     * @return If the subject has the permission
     */
    boolean hasPermission(@NotNull Object subject, int permissionLevel);

    /**
     * Get if a subject has a permission
     *
     * @param subject The subject to check
     * @param permission The permission to check
     * @return If the subject has the permission
     */
    boolean hasPermission(@NotNull Object subject, @NotNull String permission);
}
