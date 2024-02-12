package dev.neuralnexus.taterlib.player;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.hooks.permissions.LuckPermsHook;
import dev.neuralnexus.taterlib.placeholder.PlaceholderParser;

/**
 * Simple abstraction for a Minecraft player. Holds common traits between regular players and
 * proxied players.
 */
public interface SimplePlayer extends CommandSender, Connection {
    /**
     * Get the display name of the player
     *
     * @return The display name of the player
     */
    String displayName();

    /**
     * Get the server the player is on
     *
     * @return The server the player is on
     */
    String serverName();

    /**
     * Set the server the player is on
     *
     * @param serverName The server the player is on
     */
    void setServerName(String serverName);

    /**
     * Get the prefix of the player
     *
     * @return The prefix of the player
     */
    default String prefix() {
        if (!TaterAPIProvider.isHooked("luckperms")) return "";
        LuckPermsHook luckPermsHook = LuckPermsHook.get();
        String prefix = luckPermsHook.prefix(uuid());
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
        luckPermsHook.setPrefix(uuid(), prefix, priority);
    }

    /**
     * Get the suffix of the player
     *
     * @return The suffix of the player
     */
    default String suffix() {
        if (!TaterAPIProvider.isHooked("luckperms")) return "";
        LuckPermsHook luckPermsHook = LuckPermsHook.get();
        String suffix = luckPermsHook.suffix(uuid());
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
        luckPermsHook.setSuffix(uuid(), suffix, priority);
    }

    /**
     * Parse placeholders in a string
     *
     * @param input The string to parse
     * @return The parsed string
     */
    default PlaceholderParser parsePlaceholders(String input) {
        return new PlaceholderParser(input)
                .parseString("player", this.name())
                .parseString("displayname", this.displayName())
                .parseString("prefix", this.prefix())
                .parseString("suffix", this.suffix())
                .parseString("server", this.serverName())
                .parseSectionSign();
    }
}
