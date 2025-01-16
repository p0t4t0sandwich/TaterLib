/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import com.velocitypowered.api.permission.PermissionSubject;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;

import org.jetbrains.annotations.NotNull;

/** Velocity permissions provider */
public class VelocityPermissionsProvider implements PermissionsProvider {
    @Override
    public String id() {
        return "velocitypermissions";
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        return subject instanceof PermissionSubject velocitySubject
                && velocitySubject.hasPermission(permission);
    }
}
