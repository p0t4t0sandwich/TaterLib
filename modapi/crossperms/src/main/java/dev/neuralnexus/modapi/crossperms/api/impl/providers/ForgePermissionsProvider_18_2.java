/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.PermsAPI;

import net.minecraftforge.server.permission.PermissionAPI;
import net.minecraftforge.server.permission.nodes.PermissionTypes;

import org.jetbrains.annotations.NotNull;

/** Forge permissions provider */
public class ForgePermissionsProvider_18_2 implements PermissionsProvider {
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
                .getPlayer(subject)
                .filter(
                        player ->
                                PermissionAPI.getRegisteredNodes().stream()
                                        .filter(node -> node.getType() == PermissionTypes.BOOLEAN)
                                        .filter(node -> node.getNodeName().equals(permission))
                                        .anyMatch(
                                                node ->
                                                        (boolean)
                                                                node.getDefaultResolver()
                                                                        .resolve(
                                                                                player.unwrap(),
                                                                                player.getUUID())))
                .isPresent();
    }
}
