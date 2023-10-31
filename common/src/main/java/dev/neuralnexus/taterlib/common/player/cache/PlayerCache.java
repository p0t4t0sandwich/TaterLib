package dev.neuralnexus.taterlib.common.player.cache;

import dev.neuralnexus.taterlib.common.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerCache {
    private static final HashMap<UUID, Player> abstractPlayerCache = new HashMap<>();

    /**
     * Getter for the Player cache.
     * @param uuid The UUID of the player
     * @return The Player object
     */
    public static Player getPlayerFromCache(UUID uuid) {
        return abstractPlayerCache.get(uuid);
    }

    /**
     * Setter for the Player cache.
     * @param uuid The UUID of the player
     * @param player The Player object
     */
    public static void setPlayerInCache(UUID uuid, Player player) {
        abstractPlayerCache.put(uuid, player);
    }

    /**
     * Removes a Player object from the cache.
     * @param uuid The UUID of the player
     */
    public static void removePlayerFromCache(UUID uuid) {
        abstractPlayerCache.remove(uuid);
    }

    /**
     * Get a list of all players in the cache.
     * @return A list of all players in the cache
     */
    public static List<Player> getPlayersInCache() {
        return new ArrayList<>(abstractPlayerCache.values());
    }
}
