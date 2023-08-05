package dev.neuralnexus.taterlib.common.player.cache;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerCache {
    private static final HashMap<UUID, AbstractPlayer> abstractPlayerCache = new HashMap<>();

    /**
     * Getter for the AbstractPlayer cache.
     * @param uuid The UUID of the player
     * @return The AbstractPlayer object
     */
    public static AbstractPlayer getPlayerFromCache(UUID uuid) {
        return abstractPlayerCache.get(uuid);
    }

    /**
     * Setter for the AbstractPlayer cache.
     * @param uuid The UUID of the player
     * @param abstractPlayer The AbstractPlayer object
     */
    public static void setPlayerInCache(UUID uuid, AbstractPlayer abstractPlayer) {
        abstractPlayerCache.put(uuid, abstractPlayer);
    }

    /**
     * Removes a AbstractPlayer object from the cache.
     * @param uuid The UUID of the player
     */
    public static void removePlayerFromCache(UUID uuid) {
        abstractPlayerCache.remove(uuid);
    }
}
