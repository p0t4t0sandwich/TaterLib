package dev.neuralnexus.taterlib.common.event.player;

import dev.neuralnexus.taterlib.common.event.Cancellable;
import dev.neuralnexus.taterlib.common.player.Player;

import java.util.Set;

/**
 * Abstract class for player message events
 */
public interface PlayerMessageEvent extends PlayerEvent, Cancellable {
    /**
     * Getter for the message
     * @return The message
     */
    String getMessage();

    /**
     * Getter for the recipients
     * @return The recipients
     */
    Set<Player> recipients();

    /**
     * Setter for the message
     * @param message The message
     */
    void setMessage(String message);

    /**
     * Setter for the recipients
     * @param recipients The recipients
     */
    void setRecipients(Set<Player> recipients);
}
