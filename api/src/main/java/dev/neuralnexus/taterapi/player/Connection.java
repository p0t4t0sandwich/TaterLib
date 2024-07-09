/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.player;

import dev.neuralnexus.taterapi.util.ResourceKey;

/** Represents a connection to a player. */
public interface Connection {
    /**
     * Get the IP address of the player
     *
     * @return The IP address of the player
     */
    String ipAddress();

    /**
     * Get player's ping
     *
     * @return The player's ping
     */
    int ping();

    /**
     * Kick the player
     *
     * @param message The reason to kick the player
     */
    void kick(String message);

    /** Disconnect the player (kick with no message) */
    default void disconnect() {
        kick("");
    }

    /**
     * Sends a plugin message using the specified channel
     *
     * @param channel The channel to send the message on
     * @param data The message to send
     */
    void sendPluginMessage(ResourceKey channel, byte[] data);
}
