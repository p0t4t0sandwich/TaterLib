/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.impl.providers;

import dev.neuralnexus.modapi.crossperms.CrossPerms;
import dev.neuralnexus.modapi.crossperms.HasPermission;
import dev.neuralnexus.modapi.crossperms.PermissionsProvider;
import dev.neuralnexus.modapi.crossperms.mc.WMinecraftServer;
import dev.neuralnexus.modapi.crossperms.mc.WServerPlayer;

import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
import net.legacyfabric.fabric.api.permission.v1.PermissionsApiHolder;
import net.legacyfabric.fabric.api.permission.v1.PlayerPermissionsApi;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/** Legacy Fabric permissions provider */
@SuppressWarnings({"Anonymous2MethodRef", "Convert2Lambda", "deprecation", "UnstableApiUsage"})
public class LegacyFabricPermissionsProvider implements PermissionsProvider {
    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                PermissibleCommandSource.class,
                List.of(
                        new HasPermission<String, PermissibleCommandSource>() {
                            @Override
                            public boolean hasPermission(
                                    PermissibleCommandSource subject, String permission) {
                                return subject.hasPermission(permission);
                            }
                        }),
                Object.class,
                List.of(
                        new HasPermission<String, Object>() {
                            @Override
                            public boolean hasPermission(Object subject, String permission) {
                                return playerObjHasPermission(subject, permission);
                            }
                        }));
    }

    public boolean playerObjHasPermission(Object subject, String permission) {
        return WMinecraftServer.getPlayer(subject)
                .map(WServerPlayer::unwrap)
                .filter(player -> this.playerHasPermission(player, permission))
                .isPresent();
    }

    private static final Method HAS_PERMISSION;

    static {
        Method method = null;
        try {
            method =
                    PlayerPermissionsApi.class.getDeclaredMethod(
                            "hasPermission", WServerPlayer.getClazz(), String.class);
        } catch (NoSuchMethodException e) {
            CrossPerms.instance().logger().error("Failed to get method", e);
        }
        HAS_PERMISSION = method;
    }

    /**
     * Get if a player has a permission
     *
     * @param subject The player to check
     * @param permission The permission to check
     * @return If the player has the permission
     */
    private boolean playerHasPermission(Object subject, String permission) {
        if (WServerPlayer.instanceOf(subject)) {
            try {
                return (boolean)
                        HAS_PERMISSION.invoke(
                                PermissionsApiHolder.getPlayerPermissionsApi(),
                                subject,
                                permission);
            } catch (Exception e) {
                CrossPerms.instance().logger().error("Failed to invoke method", e);
            }
        }
        return false;
    }
}
