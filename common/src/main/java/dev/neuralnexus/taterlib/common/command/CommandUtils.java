package dev.neuralnexus.taterlib.common.command;

import dev.neuralnexus.taterlib.common.player.Player;

/**
 * Command utilities.
 */
public interface CommandUtils<S> {
    /**
     * Is the server dedicated?
     * @return True if the server is dedicated.
     */
    boolean isDedicated();

    /**
     * Get the sender.
     * @param source The source.
     * @return The sender.
     */
    Sender getSender(S source);

    /**
     * Get the player.
     * @param source The source.
     * @return The player.
     */
    Player getPlayer(S source);

    /**
     * Check if the source is a player.
     * @param source The source.
     */
    boolean isPlayer(S source);
}
