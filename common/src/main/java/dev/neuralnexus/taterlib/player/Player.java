package dev.neuralnexus.taterlib.player;

import dev.neuralnexus.taterlib.entity.HumanEntity;
import dev.neuralnexus.taterlib.utils.Location;

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
