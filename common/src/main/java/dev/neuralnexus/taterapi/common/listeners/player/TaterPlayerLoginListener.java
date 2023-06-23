package dev.neuralnexus.taterapi.common.listeners.player;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import dev.neuralnexus.taterapi.common.player.cache.TaterPlayerCache;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for player logins and adds the TaterPlayer to the cache.
 */
public interface TaterPlayerLoginListener {
    /**
     * Called when a player logs in.
     * @param taterPlayer The TaterPlayer.
     */
    default void taterPlayerLogin(TaterPlayer taterPlayer) {
        runTaskAsync(() -> {
            try {
                // Add the TaterPlayer to the cache
                TaterPlayerCache.setTaterPlayerInCache(taterPlayer.getUUID(), taterPlayer);

                // TODO: Apply cross-API event system
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
