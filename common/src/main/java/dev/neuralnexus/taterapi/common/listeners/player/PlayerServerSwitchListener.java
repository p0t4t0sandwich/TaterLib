package dev.neuralnexus.taterapi.common.listeners.player;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import dev.neuralnexus.taterapi.common.relay.MessageRelay;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for player logouts and sends them to the message relay.
 */
public interface PlayerServerSwitchListener {
    /**
     * Called when a player logs out, and sends it to the message relay.
     * @param taterPlayer The player.
     */
    default void taterServerSwitch(TaterPlayer taterPlayer, String toServer) {
        runTaskAsync(() -> {
            try {
                MessageRelay relay = MessageRelay.getInstance();

                // Get TaterPlayer from cache
                TaterPlayer cachedTaterPlayer = relay.getTaterPlayerFromCache(taterPlayer.getUUID());
                if (cachedTaterPlayer == null) {
                    return;
                }

                // Get fromServer
                String fromServer = taterPlayer.getServerName();

                // Update the server name and TaterPlayer object
                taterPlayer.setServerName(toServer);
                relay.setTaterPlayerInCache(taterPlayer.getUUID(), taterPlayer);

                // Relay the server switch message
                relay.sendPlayerServerSwitch(taterPlayer, fromServer, toServer);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
