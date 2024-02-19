package dev.neuralnexus.taterlib.server;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.config.ConfigLoader;
import dev.neuralnexus.taterlib.player.SimplePlayer;

import java.util.Optional;
import java.util.Set;
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
        return ConfigLoader.config().server().name();
    }

    /** Get the server's brand. */
    String brand();

    /**
     * Get the set of online players.
     *
     * @return The set of online players.
     */
    Set<SimplePlayer> onlinePlayers();

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
    default ServerType type() {
        return TaterAPIProvider.serverType();
    }
}
