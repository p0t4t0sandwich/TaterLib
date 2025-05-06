/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.entity.player.GameMode;

/** The interface for a HumanEntity */
public interface HumanEntity extends LivingEntity, InventoryHolder {
    /**
     * Get the player's current gamemode
     *
     * @return The player's current gamemode
     */
    GameMode gameMode();

    /**
     * Set the player's game mode
     *
     * @param gameMode The game mode to set
     */
    void setGameMode(GameMode gameMode);

    /**
     * Set the player's game mode
     *
     * @param id The id of the game mode to set
     */
    default void setGameMode(int id) {
        setGameMode(GameMode.fromId(id));
    }

    /**
     * Set the player's game mode
     *
     * @param name The name of the game mode to set
     */
    default void setGameMode(String name) {
        setGameMode(GameMode.fromName(name));
    }
}
