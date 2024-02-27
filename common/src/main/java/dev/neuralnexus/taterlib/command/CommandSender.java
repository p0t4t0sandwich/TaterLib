package dev.neuralnexus.taterlib.command;

import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.player.Player;

public interface CommandSender extends Permissible {
    /**
     * Get the name of the player
     *
     * @return The name of the player
     */
    String name();

    /**
     * Send a message to the command sender
     *
     * @param message The message to send
     */
    void sendMessage(String message);

    /**
     * Check if the sender is a player
     *
     * @return Whether the sender is a player
     */
    default boolean isPlayer() {
        return this instanceof Player;
    }

    /**
     * Get the player if the sender is a player
     *
     * @return The player if the sender is a player
     */
    default Player asPlayer() {
        return (Player) this;
    }
}
