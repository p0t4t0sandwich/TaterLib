package dev.neuralnexus.taterlib.common.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;

import java.util.List;

/**
 * Abstract class for an entity death event.
 */
public interface AbstractEntityDeathEvent extends AbstractEntityEvent {
    /**
     * Gets the drops for this entity.
     * @return The drops.
     */
    List<AbstractItemStack> getDrops();

    /**
     * Sets the drops for this entity.
     * @param drops The drops.
     */
    void setDrops(List<AbstractItemStack> drops);

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
