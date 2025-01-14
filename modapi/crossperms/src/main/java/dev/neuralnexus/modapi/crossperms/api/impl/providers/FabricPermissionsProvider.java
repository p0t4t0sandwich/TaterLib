/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.CrossPerms;
import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.mc.*;

import me.lucko.fabric.api.permissions.v0.Permissions;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/** Fabric permissions provider */
public class FabricPermissionsProvider implements PermissionsProvider {
    @Override
    public String id() {
        return "fabricpermissions";
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
        boolean result = false;

        GameProfile profile = this.getGameProfile(subject).orElse(null);
        if (null != profile) {
            result = this.profileHasPermission(profile, permission);
        } else if (WCommandSender.instanceOf(subject)) {
            result = this.commandSourceHasPermission(subject, permission);
        } else if (WEntity.instanceOf(subject)) {
            result = this.entityHasPermission(subject, permission);
        }
        return result | this.hasPermission(subject, 4);
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
    private boolean entityHasPermission(Object subject, String permission) {
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
    private boolean commandSourceHasPermission(Object subject, String permission) {
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
