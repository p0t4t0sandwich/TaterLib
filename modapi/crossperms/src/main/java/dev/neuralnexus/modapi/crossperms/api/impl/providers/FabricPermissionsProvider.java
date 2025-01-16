/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.CrossPerms;
import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.PermsAPI;
import dev.neuralnexus.modapi.crossperms.api.mc.*;

import me.lucko.fabric.api.permissions.v0.Permissions;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

/** Fabric permissions provider */
public class FabricPermissionsProvider implements PermissionsProvider {
    @Override
    public String id() {
        return "fabricpermissions";
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        return PermsAPI.instance()
                        .getGameProfile(subject)
                        .filter(profile -> profileHasPermission(profile, permission))
                        .isPresent()
                || (WCommandSender.instanceOf(subject)
                        && commandSourceHasPermission(subject, permission))
                || (WEntity.instanceOf(subject) && entityHasPermission(subject, permission));
    }

    /**
     * Get if a GameProfile has a permission
     *
     * @param subject The GameProfile to check
     * @param permission The permission to check
     * @return If the GameProfile has the permission
     */
    private boolean profileHasPermission(GameProfile subject, String permission) {
        try {
            return Permissions.check(subject, permission).get();
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
                            "check", WCommandSender.getClazz(), String.class);
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
