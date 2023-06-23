package dev.neuralnexus.taterapi.common.player.cache;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;

import java.util.HashMap;
import java.util.UUID;

public class TaterPlayerCache {
    private static final HashMap<UUID, TaterPlayer> taterPlayerCache = new HashMap<>();

    /**
     * Getter for the TaterPlayer cache.
     * @param uuid The UUID of the player
     * @return The PronounPlayer cache
     */
    public static TaterPlayer getTaterPlayerFromCache(UUID uuid) {
        return taterPlayerCache.get(uuid);
    }

    /**
     * Setter for the TaterPlayer cache.
     * @param uuid The UUID of the player
     * @param taterPlayer The PronounPlayer object
     */
    public static void setTaterPlayerInCache(UUID uuid, TaterPlayer taterPlayer) {
        taterPlayerCache.put(uuid, taterPlayer);
    }

    /**
     * Removes a TaterPlayer object from the cache.
     * @param uuid The UUID of the player
     */
    public static void removeTaterPlayerFromCache(UUID uuid) {
        taterPlayerCache.remove(uuid);
    }
}
