package dev.neuralnexus.taterlib.common.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.utils.Location;

/**
 * Abstract class for player respawn events.
 */
public interface AbstractPlayerRespawnEvent extends AbstractPlayerEvent {
    /**
     * Gets the location the player will respawn at.
     * @return The location.
     */
    Location getRespawnLocation();

    /**
     * Weather the respawn location is the player's bed.
     * @return True if the respawn location is the player's bed.
     */
    boolean isBedSpawn();

    /**
     * Weather the respawn location is an anchor.
     * @return True if the respawn location is an anchor.
     */
    boolean isAnchorSpawn();
}
