/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms;

import java.util.UUID;

/** Represents an entity that can have permissions. */
public interface Permissible {
    /**
     * Get the UUID of the entity
     *
     * @return The UUID of the entity
     */
    UUID uuid();

    /**
     * Get the permission level of the player
     *
     * @param permissionLevel The permission level of the player
     * @return The permission level of the player
     */
    boolean hasPermission(int permissionLevel);

    /**
     * Check if the sender has a permission
     *
     * @param permission The permission to check
     * @return Whether the player has the permission
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    default boolean hasPermission(String permission) {
        return false;
//        return TaterAPIProvider.hasPermission(this, permission);
    }
}
