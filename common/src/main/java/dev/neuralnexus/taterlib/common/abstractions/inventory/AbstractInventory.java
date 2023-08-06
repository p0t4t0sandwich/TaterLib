package dev.neuralnexus.taterlib.common.abstractions.inventory;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;

import java.util.Map;

public interface AbstractInventory {
    /**
     * Get the size of the inventory
     * @return The size of the inventory
     */
    int getSize();

    /**
     * Get an item at a specific slot from the inventory
     * @param slot The slot to get the item from
     * @return The item at the slot
     */
    AbstractItemStack getItem(int slot);

    /**
     * Set an item at a specific slot in the inventory
     * @param slot The slot to set the item at
     * @param item The item to set
     */
    void setItem(int slot, AbstractItemStack item);

    /**
     * Add an item to the inventory
     * @param item The item to add
     */
    void addItem(AbstractItemStack item);

    /**
     * Remove an item from the inventory
     * @param item The item to remove
     */
    void removeItem(AbstractItemStack item);

    /**
     * Get the name of the inventory
     * @return The name of the inventory
     */
    AbstractItemStack[] getContents();

    /**
     * Set the name of the inventory
     * @param contents The name of the inventory
     */
    void setContents(AbstractItemStack[] contents);

    /**
     * Get the name of the inventory
     * @return The name of the inventory
     */
    AbstractItemStack[] getStorageContents();

    /**
     * Set the name of the inventory
     * @param contents The name of the inventory
     */
    void setStorageContents(AbstractItemStack[] contents);

    /**
     * Get weather the inventory contains an item
     * @param item The item to check
     * @return Weather the inventory contains an item
     */
    boolean contains(AbstractItemStack item);

    /**
     * Get weather the inventory contains an item
     * @param type The type of the item
     * @return Weather the inventory contains an item
     */
    boolean contains(String type);

    /**
     * Get the name of the inventory
     * @return The name of the inventory
     */
    boolean containsAtLeast(AbstractItemStack item, int amount);

    /**
     * See if the inventory contains at least a certain amount of an item
     * @param type The type of the item
     * @param amount The amount of the item
     * @return Weather the inventory contains at least a certain amount of an item
     */
    boolean containsAtLeast(String type, int amount);

    /**
     * Get all items in the inventory
     * @return All items in the inventory
     */
    Map<Integer, AbstractItemStack> all(AbstractItemStack item);

    /**
     * Get the slot if of the first occurrence of an item in the inventory
     * @param item The item to get the slot of
     * @return The slot of the first occurrence of an item in the inventory
     */
    int first(AbstractItemStack item);

    /**
     * Get the slot if of the first occurrence of an item in the inventory
     * @param type The type of the item to get the slot of
     * @return The slot of the first occurrence of an item in the inventory
     */
    int first(String type);

    /**
     * Get the slot if of the first empty slot in the inventory
     * @return The slot of the first empty slot in the inventory
     */
    int firstEmpty();

    /**
     * Remove an item from the inventory
     * @param item The item to remove
     */
    void remove(AbstractItemStack item);

    /**
     * Remove an item from the inventory
     * @param type The type of the item to remove
     */
    void remove(String type);

    /**
     * Clear the inventory
     */
    void clear();

    /**
     * Clear a specific slot in the inventory
     * @param slot The slot to clear
     */
    void clear(int slot);
}
