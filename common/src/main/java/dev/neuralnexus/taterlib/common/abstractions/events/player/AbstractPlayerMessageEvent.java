package dev.neuralnexus.taterlib.common.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.AbstractCancellableEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;

import java.util.Set;

/**
 * Abstract class for player message events
 */
public interface AbstractPlayerMessageEvent extends AbstractPlayerEvent, AbstractCancellableEvent {
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
