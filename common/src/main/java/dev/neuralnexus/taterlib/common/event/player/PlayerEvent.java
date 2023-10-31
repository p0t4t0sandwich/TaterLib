package dev.neuralnexus.taterlib.common.event.player;

import dev.neuralnexus.taterlib.common.player.Player;

/**
 * Abstract class for player events
 */
public interface PlayerEvent {
    /**
     * Getter for the player
     * @return The player
     */
    Player getPlayer();
}
