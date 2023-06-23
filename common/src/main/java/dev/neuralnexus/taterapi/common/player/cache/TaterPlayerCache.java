package dev.neuralnexus.taterapi.common.player.cache;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;

import java.util.HashMap;
import java.util.UUID;

public class TaterPlayerCache {
    private static TaterPlayerCache singleton = null;
    private final HashMap<UUID, TaterPlayer> taterPlayerCache = new HashMap<>();

    /**
     * Constructor for the TaterPlayerCache class
     */
    public TaterPlayerCache() {
        singleton = this;
    }

    /**
     * Getter for the TaterPlayerCache singleton instance
     * @return The singleton instance
     */
    public static TaterPlayerCache getInstance() {
        return singleton;
    }

    /**
     * Getter for the TaterPlayer cache.
     * @param uuid The UUID of the player
     * @return The PronounPlayer cache
     */
    public TaterPlayer getTaterPlayerFromCache(UUID uuid) {
        return this.taterPlayerCache.get(uuid);
    }

    /**
     * Setter for the TaterPlayer cache.
     * @param uuid The UUID of the player
     * @param taterPlayer The PronounPlayer object
     */
    public void setTaterPlayerInCache(UUID uuid, TaterPlayer taterPlayer) {
        this.taterPlayerCache.put(uuid, taterPlayer);
    }

    /**
     * Removes a TaterPlayer object from the cache.
     * @param uuid The UUID of the player
     */
    public void removeTaterPlayerFromCache(UUID uuid) {
        this.taterPlayerCache.remove(uuid);
    }
}
