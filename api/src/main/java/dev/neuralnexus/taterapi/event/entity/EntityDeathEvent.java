/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.entity;

import dev.neuralnexus.taterapi.item.inventory.ItemStack;

import java.util.List;

/** Abstract class for an entity death event. */
public interface EntityDeathEvent extends EntityEvent {
    /**
     * Gets the drops for this entity.
     *
     * @return The drops.
     */
    List<ItemStack> drops();

    /**
     * Sets the drops for this entity.
     *
     * @param drops The drops.
     */
    void setDrops(List<ItemStack> drops);

    /** Clears the drops for this entity. */
    void clearDrops();

    /**
     * Gets the amount of experience dropped by this entity.
     *
     * @return The amount of experience.
     */
    int droppedExp();

    /**
     * Sets the amount of experience dropped by this entity.
     *
     * @param exp The amount of experience.
     */
    void setDroppedExp(int exp);
}
