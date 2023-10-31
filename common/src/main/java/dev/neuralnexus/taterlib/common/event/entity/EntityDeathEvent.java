package dev.neuralnexus.taterlib.common.event.entity;

import dev.neuralnexus.taterlib.common.inventory.ItemStack;

import java.util.List;

/**
 * Abstract class for an entity death event.
 */
public interface EntityDeathEvent extends EntityEvent {
    /**
     * Gets the drops for this entity.
     * @return The drops.
     */
    List<ItemStack> getDrops();

    /**
     * Sets the drops for this entity.
     * @param drops The drops.
     */
    void setDrops(List<ItemStack> drops);

    /**
     * Clears the drops for this entity.
     */
    void clearDrops();

    /**
     * Gets the amount of experience dropped by this entity.
     * @return The amount of experience.
     */
    int getDroppedExp();

    /**
     * Sets the amount of experience dropped by this entity.
     * @param exp The amount of experience.
     */
    void setDroppedExp(int exp);
}
