package dev.neuralnexus.taterlib.common.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;

import java.util.Set;

/**
 * Abstract class for player message events
 */
public interface AbstractPlayerMessageEvent extends AbstractPlayerEvent {
    /**
     * If the message is cancelled
     * @return Whether the message is cancelled
     */
    boolean isCancelled();

    /**
     * Set whether the message is cancelled
     * @param cancelled Whether the message is cancelled
     */
    void setCancelled(boolean cancelled);

    /**
     * Getter for the message
     * @return The message
     */
    String getMessage();

    /**
     * Getter for the recipients
     * @return The recipients
     */
    Set<AbstractPlayer> recipients();

    /**
     * Setter for the message
     * @param message The message
     */
    void setMessage(String message);

    /**
     * Setter for the recipients
     * @param recipients The recipients
     */
    void setRecipients(Set<AbstractPlayer> recipients);
}
