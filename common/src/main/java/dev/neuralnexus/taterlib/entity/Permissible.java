package dev.neuralnexus.taterlib.entity;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;

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
    default boolean hasPermission(String permission) {
        return TaterAPIProvider.hasPermission(this, permission);
    }
}
