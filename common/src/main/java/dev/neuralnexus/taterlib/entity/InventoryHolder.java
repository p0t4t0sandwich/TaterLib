package dev.neuralnexus.taterlib.entity;

import dev.neuralnexus.taterlib.inventory.Inventory;

/** Represents an entity that can have an inventory */
public interface InventoryHolder {
    Inventory getInventory();
}
