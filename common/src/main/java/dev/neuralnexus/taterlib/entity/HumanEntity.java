package dev.neuralnexus.taterlib.entity;

import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;

/** The interface for a HumanEntity */
public interface HumanEntity extends LivingEntity, InventoryHolder {
    /**
     * Get player's Inventory
     *
     * @return The player's Inventory
     */
    PlayerInventory getInventory();

    /**
     * Get the player's current gamemode
     *
     * @return The player's current gamemode
     */
    GameMode getGameMode();

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
