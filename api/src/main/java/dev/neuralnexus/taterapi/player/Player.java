/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.player;

import dev.neuralnexus.taterapi.entity.HumanEntity;
import dev.neuralnexus.taterapi.world.Location;

/** The interface for a Player */
public interface Player extends SimplePlayer, HumanEntity {
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

    /**
     * Allow the player to fly
     *
     * @param allow Whether the player should be allowed to fly
     */
    void allowFlight(boolean allow);

    /**
     * Check if the player is allowed to fly
     *
     * @return Whether the player is allowed to fly
     */
    boolean canFly();

    /**
     * Get whether the player is flying
     *
     * @return Whether the player is flying
     */
    boolean isFlying();

    /**
     * Set the player's flying status
     *
     * @param flying Whether the player should be flying
     */
    void setFlying(boolean flying);

    /**
     * Perform a command as the player
     *
     * @param command The command to perform
     * @return true if the command was successful, false otherwise
     */
    //    boolean performCommand(String command);

    /**
     * Check if the player is on the ground
     *
     * @return Whether the player is on the ground
     */
    //    boolean isOnGround();

    /**
     * Check if the player is sneaking
     *
     * @return Whether the player is sneaking
     */
    //    boolean isSneaking();

    /**
     * Set sneaking for the player
     *
     * @param sneak Whether the player should be sneaking
     */
    //    void setSneaking(boolean sneak);

    /**
     * Check if the player is sprinting
     *
     * @return Whether the player is sprinting
     */
    //    boolean isSprinting();

    /**
     * Set sprinting for the player
     *
     * @param sprint Whether the player should be sprinting
     */
    //    void setSprinting(boolean sprint);
}
