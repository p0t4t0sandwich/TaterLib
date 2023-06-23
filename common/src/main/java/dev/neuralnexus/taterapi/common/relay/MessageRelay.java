package dev.neuralnexus.taterapi.common.relay;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;

public interface MessageRelay {
    /**
     * Sends a message to the message relay.
     * @param message The message.
     */
    void sendPlayerMessage(TaterPlayer player, String server, String message, boolean isCancelled);

    /**
     * Sends a system message to the message relay.
     * @param message The message.
     */
    void sendSystemMessage(String message);
}
