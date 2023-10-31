package dev.neuralnexus.taterlib.common.event.player;

import dev.neuralnexus.taterlib.common.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.common.player.Player;

/**
 * Abstract class for a player death event.
 */
public interface PlayerDeathEvent extends EntityDeathEvent {
    /**
     * Gets the player.
     * TODO: Adapt getEntity() to return Player
     * @return The player.
     */
    Player getPlayer();

    /**
     * Gets the death message.
     * @return The death message.
     */
    String getDeathMessage();

    /**
     * Sets the death message.
     * @param deathMessage The death message.
     */
    void setDeathMessage(String deathMessage);

    /**
     * Gets whether the player has KeepInventory enabled.
     * @return Whether the player has KeepInventory enabled.
     */
    boolean hasKeepInventory();

    /**
     * Sets whether the player has KeepInventory enabled.
     * @param keepInventory Whether the player has KeepInventory enabled.
     */
    void setKeepInventory(boolean keepInventory);
}
