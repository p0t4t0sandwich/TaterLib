/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import com.mojang.authlib.GameProfile;
import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import dev.neuralnexus.modapi.metadata.Logger;

import me.lucko.fabric.api.permissions.v0.Permissions;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/** Fabric permissions provider */
public class FabricPermissionsProvider implements PermissionsProvider {
    private static final Logger logger = Logger.create("FabricPermissionsProvider");

    private static final Class<?> ENTITY;

    static {
        Class<?> entity = null;
        try {
            entity = Class.forName("net.minecraft.class_1297");
        } catch (ClassNotFoundException e) {
            logger.error("Failed to find Entity class", e);
        }
        ENTITY = entity;
    }

    private static final Class<?> SHARED_SUGGESTION_PROVIDER;

    static {
        Class<?> ssp = null;
        try {
            ssp = Class.forName("net.minecraft.class_2172");
        } catch (ClassNotFoundException e) {
            logger.error("Failed to find SharedSuggestionProvider class", e);
        }
        SHARED_SUGGESTION_PROVIDER = ssp;
    }

    private static final Method ENTITY_HAS_PERMISSIONS;

    static {
        Method entityHasPermissions = null;
        try {
            entityHasPermissions = ENTITY.getDeclaredMethod("method_5687", int.class);
        } catch (NoSuchMethodException e) {
            logger.error("Failed to find method_5687 method in Entity class", e);
        }
        ENTITY_HAS_PERMISSIONS = entityHasPermissions;
    }

    private static final Method SSP_HAS_PERMISSIONS;

    static {
        Method sspHasPermissions = null;
        try {
            sspHasPermissions =
                    SHARED_SUGGESTION_PROVIDER.getDeclaredMethod("method_9259", int.class);
        } catch (NoSuchMethodException e) {
            logger.error("Failed to find method_9260 method in SharedSuggestionProvider class", e);
        }
        SSP_HAS_PERMISSIONS = sspHasPermissions;
    }

    private static final Method CHECK_ENTITY;

    static {
        Method checkEntity = null;
        try {
            checkEntity = Permissions.class.getDeclaredMethod("check", ENTITY, String.class);
        } catch (NoSuchMethodException e) {
            logger.error("Failed to find check method in Permissions class", e);
        }
        CHECK_ENTITY = checkEntity;
    }

    private static final Method CHECK_SSP;

    static {
        Method checkSsp = null;
        try {
            checkSsp =
                    Permissions.class.getDeclaredMethod(
                            "check", SHARED_SUGGESTION_PROVIDER, String.class);
        } catch (NoSuchMethodException e) {
            logger.error("Failed to find check method in Permissions class", e);
        }
        CHECK_SSP = checkSsp;
    }

    @Override
    public String name() {
        return "fabricpermissions";
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        try {
            if (subject.getClass().isAssignableFrom(SHARED_SUGGESTION_PROVIDER)) {
                return (boolean) SSP_HAS_PERMISSIONS.invoke(subject, permissionLevel);
            } else if (subject.getClass().isAssignableFrom(ENTITY)) {
                return (boolean) ENTITY_HAS_PERMISSIONS.invoke(subject, permissionLevel);
            }
        } catch (Exception e) {
            logger.error("Failed to invoke check method in Permissions class", e);
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
            try {
                return Permissions.check(profile, permission).get();
            } catch (CancellationException | ExecutionException | InterruptedException  e) {
                logger.error("Failed to check permission", e);
            }
        }
        try {
            if (subject.getClass().isAssignableFrom(SHARED_SUGGESTION_PROVIDER)) {
                result = (boolean)
                        CHECK_SSP.invoke(
                                null, SHARED_SUGGESTION_PROVIDER.cast(subject), permission);
            } else if (subject.getClass().isAssignableFrom(ENTITY)) {
                result = (boolean) CHECK_ENTITY.invoke(null, ENTITY.cast(subject), permission);
            }
        } catch (Exception e) {
            logger.error("Failed to invoke check method in Permissions class", e);
        }
        return result | this.hasPermission(subject, 4);
    }
}
