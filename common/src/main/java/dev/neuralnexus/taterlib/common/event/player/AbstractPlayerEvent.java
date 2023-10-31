package dev.neuralnexus.taterlib.common.event.player;

import dev.neuralnexus.taterlib.common.player.AbstractPlayer;

/**
 * Abstract class for player events
 */
public interface AbstractPlayerEvent {
    /**
     * Getter for the player
     * @return The player
     */
    AbstractPlayer getPlayer();
}
