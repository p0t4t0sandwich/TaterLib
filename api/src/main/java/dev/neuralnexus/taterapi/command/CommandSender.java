/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.command;

import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.entity.player.Player;

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
