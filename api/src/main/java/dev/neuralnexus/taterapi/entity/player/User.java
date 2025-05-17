/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity.player;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.ServerAware;
import dev.neuralnexus.taterapi.hooks.meta.LuckPermsHook;
import dev.neuralnexus.taterapi.server.SimpleServer;

import java.lang.reflect.Type;
import java.util.Optional;

/**
 * Simple abstraction for a Minecraft player. Holds common traits between regular players and
 * proxied players.
 */
public interface User extends Subject, Identifiable, Nameable, Notifiable, ServerAware {
    @Override
    default SimpleServer server() {
        return TaterAPI.instance().server();
    }

    /**
     * Get the prefix of the player
     *
     * @return The prefix of the player
     */
    default String prefix() {
        return TaterAPI.getHook("luckperms", LuckPermsHook.class)
                .map(hook -> hook.prefix(this.uuid()))
                .orElse("");
    }

    /**
     * Set the prefix of the player
     *
     * @param prefix The prefix to set
     */
    default void setPrefix(String prefix) {
        this.setPrefix(prefix, 0);
    }

    /**
     * Set the prefix of the player
     *
     * @param prefix The prefix to set
     * @param priority The priority of the prefix
     */
    default void setPrefix(String prefix, int priority) {
        TaterAPI.getHook("luckperms", LuckPermsHook.class)
                .ifPresent(hook -> hook.setPrefix(this.uuid(), prefix, priority));
    }

    /**
     * Get the suffix of the player
     *
     * @return The suffix of the player
     */
    default String suffix() {
        return TaterAPI.getHook("luckperms", LuckPermsHook.class)
                .map(hook -> hook.suffix(this.uuid()))
                .orElse("");
    }

    /**
     * Set the suffix of the player
     *
     * @param suffix The suffix to set
     */
    default void setSuffix(String suffix) {
        this.setSuffix(suffix, 0);
    }

    /**
     * Set the suffix of the player
     *
     * @param suffix The suffix to set
     * @param priority The priority of the suffix
     */
    default void setSuffix(String suffix, int priority) {
        TaterAPI.getHook("luckperms", LuckPermsHook.class)
                .ifPresent(hook -> hook.setSuffix(this.uuid(), suffix, priority));
    }

    /**
     * Get stored metadata for the player
     *
     * @param key The key to get
     * @return The value
     */
    @Deprecated
    default Optional<Object> getMeta(String key) {
        return TaterAPI.playerDataStore().get(this, key);
    }

    /**
     * Get stored metadata for the player
     *
     * @param key The key to get
     * @param clazz The class of the object to get
     * @return The value
     */
    @Deprecated
    default <T> Optional<T> getMeta(String key, Class<T> clazz) {
        return TaterAPI.playerDataStore().get(this, key, clazz);
    }

    /**
     * Get stored metadata for the player
     *
     * @param key The key to get
     * @param type The type of the object to get
     * @return The value
     */
    @Deprecated
    default <T> Optional<T> getMeta(String key, Type type) {
        return TaterAPI.playerDataStore().get(this, key, type);
    }

    /**
     * Set stored metadata for the player
     *
     * @param key The key to set
     * @param value The value to set
     */
    @Deprecated
    default void setMeta(String key, Object value) {
        TaterAPI.playerDataStore().set(this, key, value);
    }

    /**
     * Delete stored metadata for the player
     *
     * @param key The key to delete
     */
    @Deprecated
    default void deleteMeta(String key) {
        TaterAPI.playerDataStore().delete(this, key);
    }
}
