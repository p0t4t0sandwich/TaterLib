package dev.neuralnexus.taterlib.event.player;

import dev.neuralnexus.taterlib.world.Location;

/** Abstract class for player respawn events. */
public interface PlayerRespawnEvent extends PlayerEvent {
    /**
     * Gets the location the player will respawn at.
     *
     * @return The location.
     */
    Location respawnLocation();

    /**
     * Weather the respawn location is the player's bed.
     *
     * @return True if the respawn location is the player's bed.
     */
    boolean isBedSpawn();

    /**
     * Weather the respawn location is an anchor.
     *
     * @return True if the respawn location is an anchor.
     */
    boolean isAnchorSpawn();
}
