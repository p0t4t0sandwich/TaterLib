package dev.neuralnexus.taterlib.event.player;

import dev.neuralnexus.taterlib.event.Event;
import dev.neuralnexus.taterlib.player.SimplePlayer;

/** Abstract class for player events */
public interface PlayerEvent extends Event {
    /**
     * Getter for the player
     *
     * @return The player
     */
    SimplePlayer getPlayer();
}
