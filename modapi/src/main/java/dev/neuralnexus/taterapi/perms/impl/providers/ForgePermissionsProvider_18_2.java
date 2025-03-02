/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.perms.impl.providers;

import dev.neuralnexus.taterapi.perms.HasPermission;
import dev.neuralnexus.taterapi.perms.PermissionsProvider;
import dev.neuralnexus.taterapi.perms.PermsAPI;

import net.minecraftforge.server.permission.PermissionAPI;
import net.minecraftforge.server.permission.nodes.PermissionTypes;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/** Forge permissions provider */
@SuppressWarnings({"Anonymous2MethodRef", "Convert2Lambda"})
public class ForgePermissionsProvider_18_2 implements PermissionsProvider {
    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                Object.class,
                List.of(
                        new HasPermission<String, Object>() {
                            @Override
                            public boolean hasPermission(Object subject, String permission) {
                                return playerHasPermission(subject, permission);
                            }
                        }));
    }

    public boolean playerHasPermission(Object subject, String permission) {
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
