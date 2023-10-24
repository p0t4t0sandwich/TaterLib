package dev.neuralnexus.taterlib.common.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;

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
