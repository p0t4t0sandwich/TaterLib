package dev.neuralnexus.taterlib.event.player;

import dev.neuralnexus.taterlib.event.Cancellable;
import dev.neuralnexus.taterlib.player.Player;

import java.util.Set;

/** Abstract class for player message events */
public interface PlayerMessageEvent extends PlayerEvent, Cancellable {
    /**
     * Getter for the message
     *
     * @return The message
     */
    String getMessage();

    /**
     * Setter for the message
     *
     * @param message The message
     */
    void setMessage(String message);

    /**
     * Getter for the recipients
     *
     * @return The recipients
     */
    Set<Player> recipients();

    /**
     * Setter for the recipients
     *
     * @param recipients The recipients
     */
    void setRecipients(Set<Player> recipients);
}
