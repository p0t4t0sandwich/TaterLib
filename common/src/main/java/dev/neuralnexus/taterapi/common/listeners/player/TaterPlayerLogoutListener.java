package dev.neuralnexus.taterapi.common.listeners.player;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import dev.neuralnexus.taterapi.common.player.cache.TaterPlayerCache;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for player logouts and removes the TaterPlayer from the cache.
 */
public interface TaterPlayerLogoutListener {
    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param taterPlayer The player.
     */
    default void taterPlayerLogout(TaterPlayer taterPlayer) {
        runTaskAsync(() -> {
            try {
                TaterPlayerCache playerCache = TaterPlayerCache.getInstance();

                // Remove the TaterPlayer from the cache
                playerCache.removeTaterPlayerFromCache(taterPlayer.getUUID());

                // TODO: Apply cross-API event system
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
