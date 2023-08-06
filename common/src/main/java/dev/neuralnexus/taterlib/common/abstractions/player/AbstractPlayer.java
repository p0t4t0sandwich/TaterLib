package dev.neuralnexus.taterlib.common.abstractions.player;

import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.common.placeholder.PlaceholderParser;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * The interface for an AbstractPlayer
 */
public interface AbstractPlayer {
    /**
     * Get the UUID of the player
     * @return The UUID of the player
     */
    UUID getUUID();

    /**
     * Get the name of the player
     * @return The name of the player
     */
    String getName();

    /**
     * Get the display name of the player
     * @return The display name of the player
     */
    String getDisplayName();

    /**
     * Get the server the player is on
     * @return The server the player is on
     */
    String getServerName();

    /**
     * Set the server the player is on
     * @param serverName The server the player is on
     */
    void setServerName(String serverName);

    /**
     * Send a message to the player
     * @param message The message to send
     */
    void sendMessage(String message);

    /**
     * Get player's Inventory
     * @return The player's Inventory
     */
    AbstractPlayerInventory getInventory();

    /**
     * Get player's ping
     * @return The player's ping
     */
//    int getPing();

    /**
     * Get the address of the player
     * @return The address of the player
     */
//    InetSocketAddress getAddress();

    /**
     * Kick the player
     * @param message The reason to kick the player
     */
    void kickPlayer(String message);

    /**
     * Perform a command as the player
     * @param command The command to perform
     * @return true if the command was successful, false otherwise
     */
//    boolean performCommand(String command);

    /**
     * Check if the player is on the ground
     * @return Whether the player is on the ground
     */
//    boolean isOnGround();

    /**
     * Check if the player is sneaking
     * @return Whether the player is sneaking
     */
//    boolean isSneaking();

    /**
     * Set sneaking for the player
     * @param sneak Whether the player should be sneaking
     */
//    void setSneaking(boolean sneak);

    /**
     * Check if the player is sprinting
     * @return Whether the player is sprinting
     */
//    boolean isSprinting();

    /**
     * Set sprinting for the player
     * @param sprint Whether the player should be sprinting
     */
//    void setSprinting(boolean sprint);

    /**
     * Get the prefix of the player
     * @return The prefix of the player
     */
    default String getPrefix() {
        if (!LuckPermsHook.isHooked()) return "";
        LuckPermsHook luckPermsHook = LuckPermsHook.getInstance();
        String suffix = luckPermsHook.getPrefix(getUUID());
        return suffix != null ? suffix : "";
    }

    /**
     * Get the suffix of the player
     * @return The suffix of the player
     */
    default String getSuffix() {
        if (!LuckPermsHook.isHooked()) return "";
        LuckPermsHook luckPermsHook = LuckPermsHook.getInstance();
        String suffix = luckPermsHook.getSuffix(getUUID());
        return suffix != null ? suffix : "";
    }

    /**
     * Check if the player has a permission
     * @param permission The permission to check
     * @return Whether the player has the permission
     */
    default boolean hasPermission(String permission) {
        if (!LuckPermsHook.isHooked()) return false;
        LuckPermsHook luckPermsHook = LuckPermsHook.getInstance();
        return luckPermsHook.playerHasPermission(getUUID(), permission);
    }

    /**
     * Parse placeholders in a string
     * @param input The string to parse
     * @return The parsed string
     */
    default PlaceholderParser parsePlaceholders(String input) {
        return new PlaceholderParser(input)
                .parseString("player", this.getName())
                .parseString("displayname", this.getDisplayName())
                .parseString("prefix", this.getPrefix())
                .parseString("suffix", this.getSuffix())
                .parseString("server", this.getServerName())
                .parseSectionSign();
    }
}
