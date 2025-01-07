/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import static dev.neuralnexus.modapi.crossperms.CrossPerms.ENTITY;
import static dev.neuralnexus.modapi.crossperms.CrossPerms.SHARED_SUGGESTION_PROVIDER;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.CrossPerms;
import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/** Fabric permissions provider */
public class FabricPermissionsProvider implements PermissionsProvider {
    private static final Class<?> PERMISSIONS;

    static {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("me.lucko.fabric.api.permissions.v0.Permissions");
        } catch (ClassNotFoundException e) {
            CrossPerms.instance().logger().error("Failed to find Permissions class", e);
        }
        PERMISSIONS = clazz;
    }

    private static final Method CHECK_ENTITY;

    static {
        Method checkEntity = null;
        try {
            checkEntity = PERMISSIONS.getDeclaredMethod("check", ENTITY, String.class);
        } catch (NoSuchMethodException e) {
            CrossPerms.instance()
                    .logger()
                    .error("Failed to find check method in Permissions class", e);
        }
        CHECK_ENTITY = checkEntity;
    }

    private static boolean invokeCheck_ENTITY(Object subject, String permission) {
        try {
            return (boolean) CHECK_ENTITY.invoke(null, ENTITY.cast(subject), permission);
        } catch (IllegalAccessException | InvocationTargetException e) {
            CrossPerms.instance()
                    .logger()
                    .error("Failed to invoke check method in Permissions class", e);
        }
        return false;
    }

    private static final Method CHECK_SSP;

    static {
        Method checkSsp = null;
        try {
            checkSsp =
                    PERMISSIONS.getDeclaredMethod(
                            "check", SHARED_SUGGESTION_PROVIDER, String.class);
        } catch (NoSuchMethodException e) {
            CrossPerms.instance()
                    .logger()
                    .error("Failed to find check method in Permissions class", e);
        }
        CHECK_SSP = checkSsp;
    }

    private static boolean invokeCheck_SSP(Object subject, String permission) {
        try {
            return (boolean)
                    CHECK_SSP.invoke(null, SHARED_SUGGESTION_PROVIDER.cast(subject), permission);
        } catch (IllegalAccessException | InvocationTargetException e) {
            CrossPerms.instance()
                    .logger()
                    .error("Failed to invoke check method in Permissions class", e);
        }
        return false;
    }

    private static final Method CHECK_PROFILE;

    static {
        Method checkProfile = null;
        try {
            checkProfile = PERMISSIONS.getDeclaredMethod("check", GameProfile.class, String.class);
        } catch (NoSuchMethodException e) {
            CrossPerms.instance()
                    .logger()
                    .error("Failed to find check method in Permissions class", e);
        }
        CHECK_PROFILE = checkProfile;
    }

    private static boolean invokeCheck_PROFILE(GameProfile subject, String permission) {
        try {
            return ((CompletableFuture<Boolean>) CHECK_PROFILE.invoke(null, subject, permission))
                    .get();
        } catch (IllegalAccessException | InvocationTargetException e) {
            CrossPerms.instance()
                    .logger()
                    .error("Failed to invoke check method in Permissions class", e);
        } catch (CancellationException | ExecutionException | InterruptedException e) {
            CrossPerms.instance()
                    .logger()
                    .error("Failed to get result from check method in Permissions class", e);
        }
        return false;
    }

    @Override
    public String name() {
        return "fabricpermissions";
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        if (subject.getClass().isAssignableFrom(SHARED_SUGGESTION_PROVIDER)) {
            return CrossPerms.instance()
                    .store()
                    .invokeMethod(
                            "SharedSuggestionProvider", "hasPermissions", subject, permissionLevel);
        } else if (subject.getClass().isAssignableFrom(ENTITY)) {
            return CrossPerms.instance()
                    .store()
                    .invokeMethod("Entity", "hasPermissions", subject, permissionLevel);
        }
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        Objects.requireNonNull(permission, "Permission cannot be null");
        boolean result = false;

        // TODO: Split this into a separate method
        if (subject instanceof GameProfile profile) {
            result = invokeCheck_PROFILE(profile, permission);
        }
        if (subject.getClass().isAssignableFrom(SHARED_SUGGESTION_PROVIDER)) {
            result = invokeCheck_SSP(subject, permission);
        } else if (subject.getClass().isAssignableFrom(ENTITY)) {
            result = invokeCheck_ENTITY(subject, permission);
        }
        return result | this.hasPermission(subject, 4);
    }
}
