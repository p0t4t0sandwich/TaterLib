package dev.neuralnexus.taterlib.command;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.player.Player;

import java.util.UUID;

public interface CommandSender {
    /**
     * Get the UUID of the player
     *
     * @return The UUID of the player
     */
    UUID uuid();

    /**
     * Get the name of the player
     *
     * @return The name of the player
     */
    String name();

    /**
     * Send a message to the sender
     *
     * @param message The message to send
     */
    void sendMessage(String message);

    /**
     * Get the permission level of the player
     *
     * @param permissionLevel The permission level of the player
     * @return The permission level of the player
     */
    boolean hasPermission(int permissionLevel);

    /**
     * Check if the sender has a permission
     *
     * @param permission The permission to check
     * @return Whether the player has the permission
     */
    default boolean hasPermission(String permission) {
        return TaterAPIProvider.hasPermission(this, permission);
    }

    /**
     * Check if the sender is a player
     *
     * @return Whether the sender is a player
     */
    default boolean isPlayer() {
        return this instanceof Player;
    }
}
