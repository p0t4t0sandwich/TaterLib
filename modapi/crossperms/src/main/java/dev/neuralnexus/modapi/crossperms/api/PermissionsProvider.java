/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api;

import com.mojang.authlib.GameProfile;
import dev.neuralnexus.modapi.crossperms.api.mc.WPlayerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

/** A generic provider for permissions plugins */
public interface PermissionsProvider {
    /**
     * Get the name of the provider
     *
     * @return The name of the provider
     */
    String name();

    /**
     * Get if a subject has a permission <br>
     * Can be a CommandSender, CommandSourceStack, Entity, GameProfile, String (name), UUID, Player, or any platform implementation of those objects
     *
     * @param subject The subject to check
     * @param permissionLevel The permission level to check
     * @return If the subject has the permission
     */
    boolean hasPermission(@NotNull Object subject, int permissionLevel);

    default boolean hasPermission(@NotNull GameProfile subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        return WPlayerList.hasPermissionLevel(subject, permissionLevel);
    }

    default boolean hasPermission(@NotNull UUID subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        return WPlayerList.hasPermissionLevel(subject, permissionLevel);
    }

    default boolean hasPermission(@NotNull String subject, int permissionLevel) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        return WPlayerList.hasPermissionLevel(subject, permissionLevel);
    }

    /**
     * Get if a subject has a permission <br>
     * Can be a CommandSender, CommandSourceStack, Entity, GameProfile, String (name), UUID, Player, or any platform implementation of those objects
     *
     * @param subject The subject to check
     * @param permission The permission to check
     * @return If the subject has the permission
     */
    boolean hasPermission(@NotNull Object subject, @NotNull String permission);
}
