/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.perms;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterapi.perms.impl.PermsAPIImpl;
import dev.neuralnexus.taterapi.perms.mc.WCommandSource;
import dev.neuralnexus.taterapi.perms.mc.WMinecraftServer;
import dev.neuralnexus.taterapi.perms.mc.WServerPlayer;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

/** Permissions API */
public interface PermsAPI {
    /** Get the instance of the API */
    static PermsAPI instance() {
        return PermsAPIImpl.getInstance();
    }

    /**
     * Check if a source has a permission
     *
     * @param permission The permission object
     */
    static <P, S> Predicate<S> hasPermission(P permission) {
        return source -> PermsAPIImpl.getInstance().hasPermission(source, permission);
    }

    /**
     * Get the providers for a class and a specified permission type
     *
     * @param providerType The provider type
     * @param subjectType The subject type
     */
    <P, S> List<HasPermission<P, S>> providers(Class<P> providerType, Class<S> subjectType);

    /**
     * Register a provider
     *
     * @param provider The provider to register
     */
    void registerProvider(PermissionsProvider provider);

    /**
     * Get if a subject has a permission <br>
     * Can be a CommandSender, CommandSourceStack, Entity, GameProfile, String (name), UUID, Player,
     * or any platform implementation of those objects
     *
     * @param subject The subject to check
     * @param permission The permission to check
     * @throws NullPointerException If the subject or permission is null
     * @return If the subject has the permission
     */
    @SuppressWarnings("unchecked")
    default <P, S> boolean hasPermission(@NotNull S subject, @NotNull P permission) {
        Objects.requireNonNull(subject, "Source cannot be null");
        Objects.requireNonNull(permission, "Permission cannot be null");

        List<HasPermission<P, S>> checks =
                this.providers((Class<P>) permission.getClass(), (Class<S>) subject.getClass());
        for (HasPermission<P, S> check : checks) {
            if (check.hasPermission(subject, permission)) {
                return true;
            }
        }
        // Check Object.class to see if there is a generic provider
        List<HasPermission<P, Object>> objChecks =
                this.providers((Class<P>) permission.getClass(), Object.class);
        for (HasPermission<P, Object> check : objChecks) {
            if (check.hasPermission(subject, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get if a subject has a permission <br>
     * Can be a CommandSender, CommandSourceStack, Entity, GameProfile, String (name), UUID, Player,
     * or any platform implementation of those objects
     *
     * @param subject The subject to check
     * @param permission The permission to check
     * @param defaultPermissionLevel The default permission level
     * @throws NullPointerException If the subject or permission is null
     * @return If the subject has the permission
     */
    default boolean hasPermission(
            @NotNull Object subject, @NotNull String permission, int defaultPermissionLevel)
            throws NullPointerException {
        return this.hasPermission(subject, permission)
                || this.hasPermission(subject, defaultPermissionLevel);
    }

    /**
     * Get a player from a subject
     *
     * @param subject The subject to get the player from
     * @throws NullPointerException If the subject is null
     * @return The player from the subject
     */
    default Optional<WServerPlayer> getPlayer(@NotNull Object subject) throws NullPointerException {
        Objects.requireNonNull(subject, "Subject cannot be null");
        return switch (subject) {
            case UUID uuid -> WMinecraftServer.getPlayerList().getPlayer(uuid);
            case String name -> WMinecraftServer.getPlayerList().getPlayer(name);
            case GameProfile profile -> WMinecraftServer.getPlayerList().getPlayer(profile);
            default -> {
                WServerPlayer player = null;
                if (WCommandSource.instanceOf(subject)) {
                    Object entity = WCommandSource.wrap(subject).getEntity();
                    if (WServerPlayer.instanceOf(entity)) {
                        player = WServerPlayer.wrap(entity);
                    }
                } else if (WServerPlayer.instanceOf(subject)) {
                    player = WServerPlayer.wrap(subject);
                }
                yield Optional.ofNullable(player);
            }
        };
    }

    /**
     * Get the GameProfile of a subject
     *
     * @param subject The subject to get the GameProfile of
     * @throws NullPointerException If the subject is null
     * @return The GameProfile of the subject
     */
    default Optional<GameProfile> getGameProfile(@NotNull Object subject)
            throws NullPointerException {
        Objects.requireNonNull(subject, "Subject cannot be null");
        if (subject instanceof GameProfile profile) {
            return Optional.of(profile);
        }
        return this.getPlayer(subject).map(WServerPlayer::getGameProfile);
    }
}
