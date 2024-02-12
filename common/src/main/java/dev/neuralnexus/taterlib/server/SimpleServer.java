package dev.neuralnexus.taterlib.server;

import dev.neuralnexus.taterlib.config.ConfigLoader;
import dev.neuralnexus.taterlib.player.SimplePlayer;

import java.util.Set;

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
     * Broadcast a message to the server.
     *
     * @param message The message to broadcast.
     */
    default void broadcastMessage(String message) {
        onlinePlayers().forEach(player -> player.sendMessage(message));
    }
}
