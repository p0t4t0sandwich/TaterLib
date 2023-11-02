package dev.neuralnexus.taterlib.common.player;

import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.common.utils.Position;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.common.placeholder.PlaceholderParser;

import java.util.UUID;

/**
 * The interface for a Player
 */
public interface Player extends Sender {
    /**
     * Get the display name of the player
     * @return The display name of the player
     */
    String getDisplayName();

    /**
     * Get the position of the player
     * @return The position of the player
     */
    Position getPosition();

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
     * Sends a plugin message using the specified channel
     * @param channel The channel to send the message on
     * @param data The message to send
     */
    void sendPluginMessage(String channel, byte[] data);

    /**
     * Get player's Inventory
     * @return The player's Inventory
     */
    PlayerInventory getInventory();

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
     * Set the player's spawn point
     * @param position The position to set the spawn point to
     */
    void setSpawn(Position position);

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
        String prefix = luckPermsHook.getPrefix(getUUID());
        return prefix != null ? prefix : "";
    }

    /**
     * Set the prefix of the player
     * @param prefix The prefix to set
     * @param priority The priority of the prefix
     */
    default void setPrefix(String prefix, int priority) {
        if (!LuckPermsHook.isHooked()) return;
        LuckPermsHook luckPermsHook = LuckPermsHook.getInstance();
        luckPermsHook.setPrefix(getUUID(), prefix, priority);
    }

    /**
     * Set the prefix of the player
     * @param prefix The prefix to set
     */
    default void setPrefix(String prefix) {
        setPrefix(prefix, 0);
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
     * Set the suffix of the player
     * @param suffix The suffix to set
     * @param priority The priority of the suffix
     */
    default void setSuffix(String suffix, int priority) {
        if (!LuckPermsHook.isHooked()) return;
        LuckPermsHook luckPermsHook = LuckPermsHook.getInstance();
        luckPermsHook.setSuffix(getUUID(), suffix, priority);
    }

    /**
     * Set the suffix of the player
     * @param suffix The suffix to set
     */
    default void setSuffix(String suffix) {
        setSuffix(suffix, 0);
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
