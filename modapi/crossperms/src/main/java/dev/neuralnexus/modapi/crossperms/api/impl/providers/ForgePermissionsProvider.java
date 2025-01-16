/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.PermsAPI;

import net.minecraftforge.server.permission.PermissionAPI;

import org.jetbrains.annotations.NotNull;

/** Forge permissions provider */
public class ForgePermissionsProvider implements PermissionsProvider {
    @Override
    public String id() {
        return "forgepermissions";
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        return PermsAPI.instance()
                .getGameProfile(subject)
                .filter(
                        profile ->
                                PermissionAPI.getPermissionHandler()
                                        .hasPermission(profile, permission, null))
                .isPresent();
    }
}
