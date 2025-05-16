/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import java.util.UUID;

/** Represents an object identifiable via a UUID */
public interface Identifiable {
    /**
     * Get the UUID of the object
     *
     * @return The UUID of the object
     */
    UUID uuid();

    /**
     * Check the object's permissions
     *
     * @param permission The permission to check
     * @return Whether the object has the permission
     */
    boolean hasPermission(String permission);

    /**
     * Check the object's permission
     *
     * @param permissionLevel The permission level to check
     * @return Whether the object has the permission level
     */
    boolean hasPermission(int permissionLevel);

    /**
     * Check the object's permission
     *
     * @param permission The permission to check
     * @param defaultPermissionLevel The permission level to default to
     * @return Whether the object has the permission
     */
    boolean hasPermission(String permission, int defaultPermissionLevel);
}
