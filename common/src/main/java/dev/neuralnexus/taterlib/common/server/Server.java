package dev.neuralnexus.taterlib.common.server;

import dev.neuralnexus.taterlib.common.player.Player;

import java.util.Set;

/**
 * Abstracts a Minecraft server.
 */
public interface Server {
    /**
     * Get the set of online players.
     * @return The set of online players.
     */
    Set<Player> getOnlinePlayers();
}
