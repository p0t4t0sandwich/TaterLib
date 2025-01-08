/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl.providers;

import static dev.neuralnexus.modapi.crossperms.CrossPerms.SERVER_PLAYER;

import dev.neuralnexus.modapi.crossperms.CrossPerms;
import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;

import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
import net.legacyfabric.fabric.api.permission.v1.PermissionsApiHolder;
import net.legacyfabric.fabric.api.permission.v1.PlayerPermissionsApi;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Objects;

/** Legacy Fabric permissions provider */
@SuppressWarnings({"deprecation", "UnstableApiUsage"})
public class LegacyFabricPermissionsProvider implements PermissionsProvider {
    @Override
    public String name() {
        return "fabricpermissions";
    }

    private static final Method HAS_PERMISSION;

    static {
        Method method = null;
        try {
            method =
                    PlayerPermissionsApi.class.getDeclaredMethod(
                            "hasPermission", SERVER_PLAYER, String.class);
        } catch (NoSuchMethodException e) {
            CrossPerms.instance().logger().error("Failed to get method", e);
        }
        HAS_PERMISSION = method;
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        // TODO: Reflect to query the player object
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Object subject, @NotNull String permission) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        Objects.requireNonNull(permission, "Permission cannot be null");
        boolean result = false;
        if (SERVER_PLAYER != null && SERVER_PLAYER.isInstance(subject)) {
            try {
                result =
                        (boolean)
                                HAS_PERMISSION.invoke(
                                        PermissionsApiHolder.getPlayerPermissionsApi(),
                                        subject,
                                        permission);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (subject instanceof PermissibleCommandSource commandSource) {
            result = commandSource.hasPermission(permission);
        }
        return result | this.hasPermission(subject, 4);
    }
}
