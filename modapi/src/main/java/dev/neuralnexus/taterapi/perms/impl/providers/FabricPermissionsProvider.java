/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.perms.impl.providers;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterapi.perms.CrossPerms;
import dev.neuralnexus.taterapi.perms.HasPermission;
import dev.neuralnexus.taterapi.perms.PermissionsProvider;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterapi.perms.mc.WCommandSource;
import dev.neuralnexus.taterapi.perms.mc.WEntity;
import dev.neuralnexus.taterapi.perms.mc.WServerPlayer;

import me.lucko.fabric.api.permissions.v0.Permissions;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/** Fabric permissions provider */
@SuppressWarnings({"Anonymous2MethodRef", "Convert2Lambda"})
public class FabricPermissionsProvider implements PermissionsProvider {
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
                        }),
                WCommandSource.getClazz(),
                List.of(
                        new HasPermission<String, Object>() {
                            @Override
                            public boolean hasPermission(Object subject, String permission) {
                                return commandSourceHasPermission(subject, permission);
                            }
                        }),
                WEntity.getClazz(),
                List.of(
                        new HasPermission<String, Object>() {
                            @Override
                            public boolean hasPermission(Object subject, String permission) {
                                return entityHasPermission(subject, permission);
                            }
                        }),
                WServerPlayer.getClazz(),
                List.of(
                        new HasPermission<String, Object>() {
                            @Override
                            public boolean hasPermission(Object subject, String permission) {
                                return entityHasPermission(subject, permission);
                            }
                        }));
    }

    /**
     * Get if a GameProfile has a permission
     *
     * @param subject The GameProfile to check
     * @param permission The permission to check
     * @return If the GameProfile has the permission
     */
    private boolean profileHasPermission(Object subject, String permission) {
        try {
            Optional<GameProfile> profile = PermsAPI.instance().getGameProfile(subject);
            if (profile.isEmpty()) {
                return false;
            }
            return Permissions.check(profile.get(), permission).get();
        } catch (ExecutionException | InterruptedException e) {
            return false;
        }
    }

    private static final Method CHECK_ENTITY;

    static {
        Method checkEntity = null;
        try {
            checkEntity =
                    Permissions.class.getDeclaredMethod("check", WEntity.getClazz(), String.class);
        } catch (NoSuchMethodException e) {
            CrossPerms.instance()
                    .logger()
                    .error("Failed to find check method in Permissions class", e);
        }
        CHECK_ENTITY = checkEntity;
    }

    /**
     * Get if an entity has a permission
     *
     * @param subject The entity to check
     * @param permission The permission to check
     * @return If the entity has the permission
     */
    private static boolean entityHasPermission(Object subject, String permission) {
        try {
            return (boolean) CHECK_ENTITY.invoke(null, subject, permission);
        } catch (IllegalAccessException | InvocationTargetException e) {
            CrossPerms.instance()
                    .logger()
                    .error("Failed to invoke check method in Permissions class", e);
        }
        return false;
    }

    private static final Method CHECK_COMMAND_SOURCE;

    static {
        Method checkSsp = null;
        try {
            checkSsp =
                    Permissions.class.getDeclaredMethod(
                            "check", WCommandSource.getClazz(), String.class);
        } catch (NoSuchMethodException e) {
            CrossPerms.instance()
                    .logger()
                    .error("Failed to find check method in Permissions class", e);
        }
        CHECK_COMMAND_SOURCE = checkSsp;
    }

    /**
     * Get if a CommandSource has a permission
     *
     * @param subject The CommandSource to check
     * @param permission The permission to check
     * @return If the CommandSource has the permission
     */
    private static boolean commandSourceHasPermission(Object subject, String permission) {
        try {
            return (boolean) CHECK_COMMAND_SOURCE.invoke(null, subject, permission);
        } catch (IllegalAccessException | InvocationTargetException e) {
            CrossPerms.instance()
                    .logger()
                    .error("Failed to invoke check method in Permissions class", e);
        }
        return false;
    }
}
