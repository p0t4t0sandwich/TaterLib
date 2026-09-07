/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Keys;

import org.jspecify.annotations.NonNull;

import java.util.UUID;

/** Represents an object identifiable via a UUID */
public interface Identifiable extends DataHolder {
    /**
     * Get the UUID of the object
     *
     * @return The UUID of the object
     */
    default @NonNull UUID uuid() {
        return this.getOrThrow(Keys.UUID).get();
    }

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
