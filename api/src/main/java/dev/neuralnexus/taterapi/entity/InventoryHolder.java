/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
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
