/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.item.inventory.Inventory;

/** Represents an entity that can have an inventory */
public interface InventoryHolder {
    /**
     * Get the inventory of the entity
     *
     * @return The inventory of the entity
     */
    Inventory inventory();
}
