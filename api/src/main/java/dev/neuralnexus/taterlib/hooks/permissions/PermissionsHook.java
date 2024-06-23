package dev.neuralnexus.taterlib.hooks.permissions;

import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.Hook;

/** A generic hook for permissions plugins */
public interface PermissionsHook extends Hook {
    /**
     * Get if a sender has a permission
     *
     * @param permissible The entity to check
     * @param permission The permission to check
     * @return If the entity has the permission
     */
    boolean hasPermission(Permissible permissible, String permission);
}
