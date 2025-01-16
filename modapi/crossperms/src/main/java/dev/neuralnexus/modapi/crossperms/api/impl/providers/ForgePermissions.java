/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;

import net.minecraftforge.server.permission.PermissionAPI;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/** Forge permissions provider */
public class ForgePermissions implements PermissionsProvider {
    @Override
    public String id() {
        return "forgepermissions";
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        return this.hasPermission(subject, permissionLevel, null);
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        Objects.requireNonNull(permission, "Permission cannot be null");
        return this.getGameProfile(subject)
                        .map(
                                profile ->
                                        PermissionAPI.getPermissionHandler()
                                                .hasPermission(profile, permission, null))
                        .orElse(false)
                | this.hasPermission(subject, 4);
    }
}
