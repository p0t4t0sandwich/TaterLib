package dev.neuralnexus.taterapi.common.listeners.player;

import dev.neuralnexus.taterapi.common.player.AbstractPlayer;
import dev.neuralnexus.taterapi.common.player.cache.PlayerCache;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for proxy server switches and updates the TaterPlayer's server name.
 */
public interface TaterPlayerServerSwitchListener {
    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param abstractPlayer The player.
     */
    default void taterServerSwitch(AbstractPlayer abstractPlayer, String toServer) {
        runTaskAsync(() -> {
            try {
                // Get TaterPlayer from cache
                AbstractPlayer cachedTaterPlayer = PlayerCache.getPlayerFromCache(abstractPlayer.getUUID());
                if (cachedTaterPlayer == null) {
                    return;
                }

                // Get fromServer
                String fromServer = abstractPlayer.getServerName();

                // Update the server name and TaterPlayer object
                abstractPlayer.setServerName(toServer);
                PlayerCache.setPlayerInCache(abstractPlayer.getUUID(), abstractPlayer);

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
