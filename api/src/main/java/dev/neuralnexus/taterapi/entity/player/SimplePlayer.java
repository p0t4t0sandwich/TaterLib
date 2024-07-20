/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.entity.player;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.hooks.permissions.LuckPermsHook;
import dev.neuralnexus.taterapi.placeholder.PlaceholderParser;
import dev.neuralnexus.taterapi.server.SimpleServer;

import java.lang.reflect.Type;
import java.util.Optional;

/**
 * Simple abstraction for a Minecraft player. Holds common traits between regular players and
 * proxied players.
 */
public interface SimplePlayer extends CommandSender {
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
    SimpleServer server();

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
     * Get stored metadata for the player
     *
     * @param key The key to get
     * @return The value
     */
    default Optional<Object> getMeta(String key) {
        return TaterAPIProvider.playerDataStore().get(this, key);
    }

    /**
     * Get stored metadata for the player
     *
     * @param key The key to get
     * @param clazz The class of the object to get
     * @return The value
     */
    default <T> Optional<T> getMeta(String key, Class<T> clazz) {
        return TaterAPIProvider.playerDataStore().get(this, key, clazz);
    }

    /**
     * Get stored metadata for the player
     *
     * @param key The key to get
     * @param type The type of the object to get
     * @return The value
     */
    default <T> Optional<T> getMeta(String key, Type type) {
        return TaterAPIProvider.playerDataStore().get(this, key, type);
    }

    /**
     * Set stored metadata for the player
     *
     * @param key The key to set
     * @param value The value to set
     */
    default void setMeta(String key, Object value) {
        TaterAPIProvider.playerDataStore().set(this, key, value);
    }

    /**
     * Delete stored metadata for the player
     *
     * @param key The key to delete
     */
    default void deleteMeta(String key) {
        TaterAPIProvider.playerDataStore().delete(this, key);
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
                .parseString("server", this.server() != null ? this.server().name() : "noServer")
                .parseSectionSign();
    }
}
