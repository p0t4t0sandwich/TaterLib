/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.player;

import dev.neuralnexus.taterapi.world.Location;

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
