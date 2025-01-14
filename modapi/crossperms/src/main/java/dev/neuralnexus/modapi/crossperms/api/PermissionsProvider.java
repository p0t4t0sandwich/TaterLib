/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.api.mc.*;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

/** A generic provider for permissions plugins */
public interface PermissionsProvider {
    /**
     * Get the id of the provider
     *
     * @return The id of the provider
     */
    String id();

    /**
     * Get if a subject has a permission <br>
     * Can be a CommandSender, CommandSourceStack, Entity, GameProfile, String (name), UUID, Player,
     * or any platform implementation of those objects
     *
     * @param subject The subject to check
     * @param permissionLevel The permission level to check
     * @return If the subject has the permission
     */
    boolean hasPermission(@NotNull Object subject, int permissionLevel);

    default boolean hasPermission(
            @NotNull Object subject, int permissionLevel, Function<Object, Boolean> callback) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        if (null == callback) {
            callback = (sub) -> false;
        }
        if (WPlayerList.is13_up) {
            if (WCommandSender.instanceOf(subject)) {
                return WCommandSender.wrap(subject).hasPermission(permissionLevel);
            } else if (WEntity.instanceOf(subject)) {
                return WServerPlayer.wrap(subject).hasPermission(permissionLevel);
            }
        }
        return switch (subject) {
            case GameProfile profile -> WPlayerList.hasPermissionLevel(profile, permissionLevel);
            case UUID uuid -> WPlayerList.hasPermissionLevel(uuid, permissionLevel);
            case String name -> WPlayerList.hasPermissionLevel(name, permissionLevel);
            default -> callback.apply(subject);
        };
    }

    /**
     * Get if a subject has a permission <br>
     * Can be a CommandSender, CommandSourceStack, Entity, GameProfile, String (name), UUID, Player,
     * or any platform implementation of those objects
     *
     * @param subject The subject to check
     * @param permission The permission to check
     * @return If the subject has the permission
     */
    boolean hasPermission(@NotNull Object subject, @NotNull String permission);

    default Optional<GameProfile> getGameProfile(@NotNull Object subject) {
        Objects.requireNonNull(subject, "Subject cannot be null");
        return Optional.ofNullable(
                switch (subject) {
                    case GameProfile gameProfile -> gameProfile;
                    case UUID uuid -> WMinecraftServer.getPlayerList()
                            .getPlayer(uuid)
                            .map(WServerPlayer::getGameProfile)
                            .orElse(null);
                    case String name -> WMinecraftServer.getPlayerList()
                            .getPlayer(name)
                            .map(WServerPlayer::getGameProfile)
                            .orElse(null);
                    default -> {
                        if (WServerPlayer.instanceOf(subject)) {
                            yield WServerPlayer.wrap(subject).getGameProfile();
                        }
                        yield null;
                    }
                });
    }
}
