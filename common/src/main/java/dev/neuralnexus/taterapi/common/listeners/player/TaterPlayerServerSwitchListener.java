package dev.neuralnexus.taterapi.common.listeners.player;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import dev.neuralnexus.taterapi.common.player.cache.TaterPlayerCache;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for proxy server switches and updates the TaterPlayer's server name.
 */
public interface TaterPlayerServerSwitchListener {
    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param taterPlayer The player.
     */
    default void taterServerSwitch(TaterPlayer taterPlayer, String toServer) {
        runTaskAsync(() -> {
            try {
                // Get TaterPlayer from cache
                TaterPlayer cachedTaterPlayer = TaterPlayerCache.getTaterPlayerFromCache(taterPlayer.getUUID());
                if (cachedTaterPlayer == null) {
                    return;
                }

                // Get fromServer
                String fromServer = taterPlayer.getServerName();

                // Update the server name and TaterPlayer object
                taterPlayer.setServerName(toServer);
                TaterPlayerCache.setTaterPlayerInCache(taterPlayer.getUUID(), taterPlayer);

                // TODO: Apply cross-API event system
                // Relay the server switch message
                // relay.sendPlayerServerSwitch(taterPlayer, fromServer, toServer);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
