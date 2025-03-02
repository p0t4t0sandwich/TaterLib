/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.perms.impl.integrations;

import dev.neuralnexus.taterapi.perms.HasPermission;
import dev.neuralnexus.taterapi.perms.PermissionsProvider;
import dev.neuralnexus.taterapi.perms.PermsAPI;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"Anonymous2MethodRef", "Convert2Lambda"})
public class LuckPermsPermissionsProvider implements PermissionsProvider {
    private final LuckPerms luckPerms;

    public LuckPermsPermissionsProvider() {
        this.luckPerms = LuckPermsProvider.get();
    }

    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                Object.class,
                List.of(
                        new HasPermission<String, Object>() {
                            @Override
                            public boolean hasPermission(Object subject, String permission) {
                                return profileHasPermission(subject, permission);
                            }
                        }));
    }

    private boolean profileHasPermission(Object subject, String permission) {
        return PermsAPI.instance()
                .getGameProfile(subject)
                .map(profile -> this.luckPerms.getUserManager().getUser(profile.getId()))
                .map(
                        user ->
                                user.getCachedData()
                                        .getPermissionData()
                                        .checkPermission(permission)
                                        .asBoolean())
                .orElse(false);
    }
}
