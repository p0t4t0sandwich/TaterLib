package dev.neuralnexus.taterapi.common.listeners.player;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import dev.neuralnexus.taterapi.common.relay.MessageRelay;

import java.util.HashMap;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

/**
 * Listens for player messages and sends them to the message relay.
 */
public interface PlayerMessageListener {
    HashMap<String, Long> chatFilter = new HashMap<>();

    /**
     * Filters a message.
     * @param message The message
     * @return Whether the message was filtered
     */
    default boolean filterMessage(String message) {
        // Check if message is in chat filter
        if (chatFilter.containsKey(message)) {
            // Check if message is still in chat filter
            if (chatFilter.get(message) > System.currentTimeMillis()) {
                // Cancel message
                return true;
            } else {
                // Remove message from chat filter
                chatFilter.remove(message);
            }
        } else {
            // Add message to chat filter
            chatFilter.put(message, System.currentTimeMillis() + 1000);
        }
        return false;
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     * @param taterPlayer The player
     * @param message The message
     * @param isCancelled Whether the message was cancelled
     */
    default void taterPlayerMessage(TaterPlayer taterPlayer, String message, boolean isCancelled) {
        runTaskAsync(() -> {
            try {
                MessageRelay relay = MessageRelay.getInstance();
                // Send message through relay
                relay.sendMessage(taterPlayer, taterPlayer.getServerName(), message, isCancelled);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
