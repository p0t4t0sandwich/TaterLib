package dev.neuralnexus.taterapi.common.relay;

import dev.neuralnexus.taterapi.common.player.AbstractPlayer;

public interface MessageRelay {
    /**
     * Sends a message to the message relay.
     * @param message The message.
     */
    void sendPlayerMessage(AbstractPlayer player, String server, String message, boolean isCancelled);

    /**
     * Sends a system message to the message relay.
     * @param message The message.
     */
    void sendSystemMessage(String message);
}
