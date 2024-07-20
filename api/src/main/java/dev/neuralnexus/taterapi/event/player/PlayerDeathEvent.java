/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.entity.EntityDeathEvent;

/** Abstract class for a player death event. */
public interface PlayerDeathEvent extends EntityDeathEvent {
    /**
     * Gets the player. TODO: Adapt getEntity() to return Player
     *
     * @return The player.
     */
    Player player();

    /**
     * Gets the death message.
     *
     * @return The death message.
     */
    String deathMessage();

    /**
     * Sets the death message.
     *
     * @param deathMessage The death message.
     */
    void setDeathMessage(String deathMessage);

    /**
     * Gets whether the player has KeepInventory enabled.
     *
     * @return Whether the player has KeepInventory enabled.
     */
    boolean keepInventory();

    /**
     * Sets whether the player has KeepInventory enabled.
     *
     * @param keepInventory Whether the player has KeepInventory enabled.
     */
    void setKeepInventory(boolean keepInventory);
}
