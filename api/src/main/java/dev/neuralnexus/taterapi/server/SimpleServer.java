/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.server;

import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Simple abstraction for a Minecraft server. Holds common traits between regular servers and
 * proxies.
 */
public interface SimpleServer {
    /**
     * Get the name of the server.
     *
     * @return The name of the server.
     */
    default String name() {
        return TaterAPIProvider.serverName();
    }

    /** Get the server's brand. */
    String brand();

    /**
     * Get the set of online players.
     *
     * @return The set of online players.
     */
    List<SimplePlayer> onlinePlayers();

    /**
     * Get a player by their name.
     *
     * @param name The name of the player.
     * @return The player, if found.
     */
    default Optional<SimplePlayer> getPlayer(String name) {
        return onlinePlayers().stream().filter(player -> player.name().equals(name)).findFirst();
    }

    /**
     * Get a player by their UUID.
     *
     * @param uuid The UUID of the player.
     * @return The player, if found.
     */
    default Optional<SimplePlayer> getPlayer(UUID uuid) {
        return onlinePlayers().stream().filter(player -> player.uuid().equals(uuid)).findFirst();
    }

    /**
     * Broadcast a message to the server.
     *
     * @param message The message to broadcast.
     */
    default void broadcastMessage(String message) {
        onlinePlayers().forEach(player -> player.sendMessage(message));
    }

    /**
     * Get the server's version.
     *
     * @return The server's version.
     */
    default MinecraftVersion version() {
        return TaterAPIProvider.minecraftVersion();
    }

    /**
     * Get the server's type.
     *
     * @return The server's type.
     */
    default Platform type() {
        return TaterAPIProvider.platform();
    }
}
