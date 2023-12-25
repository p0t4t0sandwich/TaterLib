package dev.neuralnexus.taterlib.player;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.hooks.permissions.LuckPermsHook;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.utils.Location;

/** The interface for a Player */
public interface Player extends Sender, Entity {
    /**
     * Get the display name of the player
     *
     * @return The display name of the player
     */
    String getDisplayName();

    /**
     * Get the IP address of the player
     *
     * @return The IP address of the player
     */
    String getIPAddress();

    /**
     * Get the server the player is on
     *
     * @return The server the player is on
     */
    String getServerName();

    /**
     * Set the server the player is on
     *
     * @param serverName The server the player is on
     */
    void setServerName(String serverName);

    /**
     * Sends a plugin message using the specified channel
     *
     * @param channel The channel to send the message on
     * @param data The message to send
     */
    void sendPluginMessage(String channel, byte[] data);

    /**
     * Get player's Inventory
     *
     * @return The player's Inventory
     */
    PlayerInventory getInventory();

    /**
     * Get player's ping
     *
     * @return The player's ping
     */
    int getPing();

    /**
     * Get the address of the player
     *
     * @return The address of the player
     */
    //    InetSocketAddress getAddress();

    /**
     * Kick the player
     *
     * @param message The reason to kick the player
     */
    void kickPlayer(String message);

    /**
     * Set the player's spawn point
     *
     * @param location The location to set the spawn point to
     */
    void setSpawn(Location location, boolean forced);

    /**
     * Set the player's spawn point
     *
     * @param location The location to set the spawn point to
     */
    default void setSpawn(Location location) {
        setSpawn(location, false);
    }

    /**
     * Get the player's current gamemode
     *
     * @return The player's current gamemode
     */
    GameMode getGameMode();

    /**
     * Set the player's game mode
     *
     * @param gameMode The game mode to set
     */
    void setGameMode(GameMode gameMode);

    /**
     * Set the player's game mode
     *
     * @param id The id of the game mode to set
     */
    default void setGameMode(int id) {
        setGameMode(GameMode.fromId(id));
    }

    /**
     * Set the player's game mode
     *
     * @param name The name of the game mode to set
     */
    default void setGameMode(String name) {
        setGameMode(GameMode.fromName(name));
    }

    /**
     * Perform a command as the player
     *
     * @param command The command to perform
     * @return true if the command was successful, false otherwise
     */
    //    boolean performCommand(String command);

    /**
     * Check if the player is on the ground
     *
     * @return Whether the player is on the ground
     */
    //    boolean isOnGround();

    /**
     * Check if the player is sneaking
     *
     * @return Whether the player is sneaking
     */
    //    boolean isSneaking();

    /**
     * Set sneaking for the player
     *
     * @param sneak Whether the player should be sneaking
     */
    //    void setSneaking(boolean sneak);

    /**
     * Check if the player is sprinting
     *
     * @return Whether the player is sprinting
     */
    //    boolean isSprinting();

    /**
     * Set sprinting for the player
     *
     * @param sprint Whether the player should be sprinting
     */
    //    void setSprinting(boolean sprint);

    /**
     * Get the prefix of the player
     *
     * @return The prefix of the player
     */
    default String getPrefix() {
        if (!TaterAPIProvider.isHooked("luckperms")) return "";
        LuckPermsHook luckPermsHook = LuckPermsHook.get();
        String prefix = luckPermsHook.getPrefix(getUniqueId());
        return prefix != null ? prefix : "";
    }

    /**
     * Set the prefix of the player
     *
     * @param prefix The prefix to set
     */
    default void setPrefix(String prefix) {
        setPrefix(prefix, 0);
    }

    /**
     * Set the prefix of the player
     *
     * @param prefix The prefix to set
     * @param priority The priority of the prefix
     */
    default void setPrefix(String prefix, int priority) {
        if (!TaterAPIProvider.isHooked("luckperms")) return;
        LuckPermsHook luckPermsHook = LuckPermsHook.get();
        luckPermsHook.setPrefix(getUniqueId(), prefix, priority);
    }

    /**
     * Get the suffix of the player
     *
     * @return The suffix of the player
     */
    default String getSuffix() {
        if (!TaterAPIProvider.isHooked("luckperms")) return "";
        LuckPermsHook luckPermsHook = LuckPermsHook.get();
        String suffix = luckPermsHook.getSuffix(getUniqueId());
        return suffix != null ? suffix : "";
    }

    /**
     * Set the suffix of the player
     *
     * @param suffix The suffix to set
     */
    default void setSuffix(String suffix) {
        setSuffix(suffix, 0);
    }

    /**
     * Set the suffix of the player
     *
     * @param suffix The suffix to set
     * @param priority The priority of the suffix
     */
    default void setSuffix(String suffix, int priority) {
        if (!TaterAPIProvider.isHooked("luckperms")) return;
        LuckPermsHook luckPermsHook = LuckPermsHook.get();
        luckPermsHook.setSuffix(getUniqueId(), suffix, priority);
    }

    /**
     * Parse placeholders in a string
     *
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
