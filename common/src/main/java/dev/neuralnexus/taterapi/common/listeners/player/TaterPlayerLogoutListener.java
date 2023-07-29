package dev.neuralnexus.taterapi.common.listeners.player;

import dev.neuralnexus.taterapi.common.player.AbstractPlayer;
import dev.neuralnexus.taterapi.common.player.cache.PlayerCache;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for player logouts and removes the TaterPlayer from the cache.
 */
public interface TaterPlayerLogoutListener {
    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param abstractPlayer The player.
     */
    default void taterPlayerLogout(AbstractPlayer abstractPlayer) {
        runTaskAsync(() -> {
            try {
                // Remove the TaterPlayer from the cache
                PlayerCache.removePlayerFromCache(abstractPlayer.getUUID());

                // TODO: Apply cross-API event system
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
