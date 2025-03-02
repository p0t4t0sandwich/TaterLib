/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.item.inventory;

import dev.neuralnexus.taterapi.resource.ResourceKey;

import java.util.ArrayList;
import java.util.List;

public interface Inventory {
    /**
     * Get the size of the inventory
     *
     * @return The size of the inventory
     */
    int size();

    /**
     * Get an item at a specific slot from the inventory
     *
     * @param slot The slot to get the item from
     * @return The item at the slot
     */
    ItemStack get(int slot);

    /**
     * Get a list of items from the inventory
     *
     * @param slots The slots to get the items from
     */
    default List<ItemStack> get(int... slots) {
        List<ItemStack> items = new ArrayList<>();
        for (int slot : slots) {
            items.add(get(slot));
        }
        return items;
    }

    /**
     * Set an item at a specific slot in the inventory
     *
     * @param slot The slot to set the item at
     * @param item The item to set
     */
    void set(int slot, ItemStack item);

    /**
     * Add an item to the inventory
     *
     * @param item The item to add
     */
    void add(ItemStack item);

    /**
     * Remove an item from the inventory
     *
     * @param item The item to remove
     */
    default void remove(ItemStack item) {
        this.remove(item.type());
    }

    /**
     * Remove an item from the inventory
     *
     * @param type The type of the item to remove
     */
    void remove(ResourceKey type);

    /** Clear the inventory */
    default void clear() {
        for (int i = 0; i < this.size(); i++) {
            this.clear(i);
        }
    }

    /**
     * Clear a specific slot in the inventory
     *
     * @param slot The slot to clear
     */
    void clear(int slot);

    /**
     * Get the name of the inventory
     *
     * @return The name of the inventory
     */
    List<ItemStack> contents();

    /**
     * Set the name of the inventory
     *
     * @param contents The name of the inventory
     */
    void setContents(List<ItemStack> contents);

    /**
     * Get weather the inventory contains an item
     *
     * @param item The item to check
     * @return Weather the inventory contains an item
     */
    default boolean contains(ItemStack item) {
        return this.containsAtLeast(item, 1);
    }

    /**
     * Get weather the inventory contains an item
     *
     * @param type The type of the item
     * @return Weather the inventory contains an item
     */
    default boolean contains(ResourceKey type) {
        return this.containsAtLeast(type, 1);
    }

    /**
     * Get the name of the inventory
     *
     * @return The name of the inventory
     */
    default boolean containsAtLeast(ItemStack item, int amount) {
        return this.containsAtLeast(item.type(), amount);
    }

    /**
     * See if the inventory contains at least a certain amount of an item
     *
     * @param type The type of the item
     * @param amount The amount of the item
     * @return Weather the inventory contains at least a certain amount of an item
     */
    default boolean containsAtLeast(ResourceKey type, int amount) {
        int count = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).type().equals(type)) {
                count += get(i).count();
            }
        }
        return count >= amount;
    }

    /**
     * Get the slot if of the first occurrence of an item in the inventory
     *
     * @param item The item to get the slot of
     * @return The slot of the first occurrence of an item in the inventory
     */
    default int first(ItemStack item) {
        return this.first(item.type());
    }

    /**
     * Get the slot if of the first occurrence of an item in the inventory
     *
     * @param type The type of the item to get the slot of
     * @return The slot of the first occurrence of an item in the inventory
     */
    default int first(ResourceKey type) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).type().equals(type)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get the slot if of the first empty slot in the inventory
     *
     * @return The slot of the first empty slot in the inventory
     */
    default int firstEmpty() {
        for (int i = 0; i < this.size(); i++) {
            if (get(i) == null || get(i).type().asString().equals("minecraft:air")) {
                return i;
            }
        }
        return -1;
    }
}
