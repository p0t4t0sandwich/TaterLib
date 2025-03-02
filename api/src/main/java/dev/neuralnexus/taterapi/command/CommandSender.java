/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.command;

import dev.neuralnexus.taterapi.entity.Identifiable;

public interface CommandSender extends Identifiable {
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
    default void sendMessage(String message) {}
}
