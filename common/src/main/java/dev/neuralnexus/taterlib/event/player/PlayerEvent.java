package dev.neuralnexus.taterlib.event.player;

import dev.neuralnexus.taterlib.player.Player;

/** Abstract class for player events */
public interface PlayerEvent {
    /**
     * Getter for the player
     *
     * @return The player
     */
    Player getPlayer();
}
