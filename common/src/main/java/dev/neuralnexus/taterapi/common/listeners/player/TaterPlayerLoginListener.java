package dev.neuralnexus.taterapi.common.listeners.player;

import dev.neuralnexus.taterapi.common.player.AbstractPlayer;
import dev.neuralnexus.taterapi.common.player.cache.PlayerCache;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for player logins and adds the TaterPlayer to the cache.
 */
public interface TaterPlayerLoginListener {
    /**
     * Called when a player logs in.
     * @param abstractPlayer The TaterPlayer.
     */
    default void taterPlayerLogin(AbstractPlayer abstractPlayer) {
        runTaskAsync(() -> {
            try {
                // Add the TaterPlayer to the cache
                PlayerCache.setPlayerInCache(abstractPlayer.getUUID(), abstractPlayer);

                // TODO: Apply cross-API event system
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
