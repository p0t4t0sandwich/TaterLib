package dev.neuralnexus.taterlib.event.player;

import dev.neuralnexus.taterlib.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.player.Player;

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
