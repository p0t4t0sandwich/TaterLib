/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.inventory.Inventory;

/** Represents an entity that can have an inventory */
public interface InventoryHolder {
    /**
     * Get the inventory of the entity
     *
     * @return The inventory of the entity
     */
    Inventory inventory();
}
