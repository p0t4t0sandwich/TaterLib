/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity.player;

import dev.neuralnexus.taterapi.entity.HumanEntity;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.meta.annotations.Range;
import dev.neuralnexus.taterapi.meta.annotations.VersionFeature;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;

/** The interface for a Player */
public interface Player extends HumanEntity, User {
    /**
     * Get player's Inventory
     *
     * @return The player's Inventory
     */
    PlayerInventory inventory();

    /**
     * Allow the player to fly
     *
     * @param allow Whether the player should be allowed to fly
     */
    @VersionFeature(
            name = "Player#allowFlight(boolean)",
            incompatible = @Range(MinecraftVersion.B1_7_3))
    void allowFlight(boolean allow);

    /**
     * Check if the player is allowed to fly
     *
     * @return Whether the player is allowed to fly
     */
    @VersionFeature(name = "Player#canFly()", incompatible = @Range(MinecraftVersion.B1_7_3))
    boolean canFly();

    /**
     * Get whether the player is flying
     *
     * @return Whether the player is flying
     */
    @VersionFeature(name = "Player#isFlying()", incompatible = @Range(MinecraftVersion.B1_7_3))
    boolean isFlying();

    /**
     * Set the player's flying status
     *
     * @param flying Whether the player should be flying
     */
    @VersionFeature(name = "Player#setFlying()", incompatible = @Range(MinecraftVersion.B1_7_3))
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
