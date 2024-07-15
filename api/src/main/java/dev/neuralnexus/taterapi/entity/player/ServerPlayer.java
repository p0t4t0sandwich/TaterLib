package dev.neuralnexus.taterapi.entity.player;

import dev.neuralnexus.taterapi.world.Location;

/** The interface for a ServerPlayer */
public interface ServerPlayer extends Player, Connection {
    /**
     * Set the player's spawn point
     *
     * @param location The location to set the spawn point to
     */
    void setSpawn(Location location, boolean forced);

    /**
     * Set the player's spawn point
     *
     * @param location The location to set the spawn point to
     */
    default void setSpawn(Location location) {
        setSpawn(location, false);
    }
}
