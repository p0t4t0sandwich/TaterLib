package dev.neuralnexus.taterlib.common.relay;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;

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
