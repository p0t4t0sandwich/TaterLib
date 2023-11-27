package dev.neuralnexus.taterlib.common.command;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;

import java.util.UUID;

public interface Sender {
    /**
     * Get the UUID of the player
     *
     * @return The UUID of the player
     */
    UUID getUniqueId();

    /**
     * Get the name of the player
     *
     * @return The name of the player
     */
    String getName();

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
     * Check if the player has a permission
     *
     * @param permission The permission to check
     * @return Whether the player has the permission
     */
    default boolean hasPermission(String permission) {
        if (!TaterAPIProvider.isHooked("luckperms")) return false;
        LuckPermsHook luckPermsHook = LuckPermsHook.get();
        return luckPermsHook.playerHasPermission(getUniqueId(), permission);
    }
}
