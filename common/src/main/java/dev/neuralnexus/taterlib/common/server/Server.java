package dev.neuralnexus.taterlib.common.server;

import dev.neuralnexus.taterlib.common.player.Player;

import java.util.Set;

/** Abstracts a Minecraft server. */
public interface Server {
    /**
     * Get the name of the server.
     *
     * @return The name of the server.
     */
    String getName();

    /**
     * Get the set of online players.
     *
     * @return The set of online players.
     */
    Set<Player> getOnlinePlayers();

    /**
     * Broadcast a message to the server.
     *
     * @param message The message to broadcast.
     */
    default void broadcastMessage(String message) {
        getOnlinePlayers().forEach(player -> player.sendMessage(message));
    }
}
