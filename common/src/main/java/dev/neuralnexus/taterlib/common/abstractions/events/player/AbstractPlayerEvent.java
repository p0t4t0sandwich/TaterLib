package dev.neuralnexus.taterlib.common.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;

public interface AbstractPlayerEvent {
    /**
     * Getter for the player
     * @return The player
     */
    AbstractPlayer getPlayer();
}
