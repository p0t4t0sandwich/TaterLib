/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.mc;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.CrossPerms;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/** Represents a Minecraft server */
public class WMinecraftServer {
    private static Object server;

    @ApiStatus.Internal
    public static Object setServer(Object server) {
        return WMinecraftServer.server = server;
    }

    public static WPlayerList getPlayerList() {
        return WPlayerList.wrap(
                CrossPerms.instance()
                        .store()
                        .invokeMethod("MinecraftServer", "getPlayerList", server));
    }

    /**
     * Get a player from a subject
     *
     * @param subject The subject to get the player from
     * @throws NullPointerException If the subject is null
     * @return The player from the subject
     */
    // TODO: Possibly have a similar method as part of PermsAPI
    public static Optional<WServerPlayer> getPlayer(@NotNull Object subject)
            throws NullPointerException {
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
}
