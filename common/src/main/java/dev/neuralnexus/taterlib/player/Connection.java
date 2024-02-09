package dev.neuralnexus.taterlib.player;

/** Represents a connection to a player. */
public interface Connection {
    /**
     * Get the IP address of the player
     *
     * @return The IP address of the player
     */
    String getIPAddress();

    /**
     * Get player's ping
     *
     * @return The player's ping
     */
    int getPing();

    /**
     * Kick the player
     *
     * @param message The reason to kick the player
     */
    void kickPlayer(String message);

    /** Disconnect the player (kick with no message) */
    default void disconnect() {
        kickPlayer("");
    }

    /**
     * Sends a plugin message using the specified channel
     *
     * @param channel The channel to send the message on
     * @param data The message to send
     */
    void sendPluginMessage(String channel, byte[] data);
}
