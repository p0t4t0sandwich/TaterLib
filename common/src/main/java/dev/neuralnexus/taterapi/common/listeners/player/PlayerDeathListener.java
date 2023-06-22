package dev.neuralnexus.taterapi.common.listeners.player;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import dev.neuralnexus.taterapi.common.relay.MessageRelay;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for player deaths and sends them to the message relay.
 */
public interface PlayerDeathListener {
    /**
     * Called when a player dies, and sends it to the message relay.
     * @param taterPlayer The player.
     * @param deathMessage The death message.
     */
    default void taterPlayerDeath(TaterPlayer taterPlayer, String deathMessage) {
        runTaskAsync(() -> {
            try {
                MessageRelay relay = MessageRelay.getInstance();
                // Send death message through relay
                relay.sendSystemMessage(taterPlayer.getServerName(), deathMessage);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
