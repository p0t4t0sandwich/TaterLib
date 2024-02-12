package dev.neuralnexus.taterlib.inventory;

import java.util.Map;

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
    ItemStack item(int slot);

    /**
     * Set an item at a specific slot in the inventory
     *
     * @param slot The slot to set the item at
     * @param item The item to set
     */
    void setItem(int slot, ItemStack item);

    /**
     * Add an item to the inventory
     *
     * @param item The item to add
     */
    void addItem(ItemStack item);

    /**
     * Remove an item from the inventory
     *
     * @param item The item to remove
     */
    void removeItem(ItemStack item);

    /**
     * Get the name of the inventory
     *
     * @return The name of the inventory
     */
    ItemStack[] contents();

    /**
     * Set the name of the inventory
     *
     * @param contents The name of the inventory
     */
    void setContents(ItemStack[] contents);

    /**
     * Get the name of the inventory
     *
     * @return The name of the inventory
     */
    ItemStack[] storageContents();

    /**
     * Set the name of the inventory
     *
     * @param contents The name of the inventory
     */
    void setStorageContents(ItemStack[] contents);

    /**
     * Get weather the inventory contains an item
     *
     * @param item The item to check
     * @return Weather the inventory contains an item
     */
    boolean contains(ItemStack item);

    /**
     * Get weather the inventory contains an item
     *
     * @param type The type of the item
     * @return Weather the inventory contains an item
     */
    boolean contains(String type);

    /**
     * Get the name of the inventory
     *
     * @return The name of the inventory
     */
    boolean containsAtLeast(ItemStack item, int amount);

    /**
     * See if the inventory contains at least a certain amount of an item
     *
     * @param type The type of the item
     * @param amount The amount of the item
     * @return Weather the inventory contains at least a certain amount of an item
     */
    boolean containsAtLeast(String type, int amount);

    /**
     * Get all items in the inventory
     *
     * @return All items in the inventory
     */
    Map<Integer, ItemStack> all(ItemStack item);

    /**
     * Get the slot if of the first occurrence of an item in the inventory
     *
     * @param item The item to get the slot of
     * @return The slot of the first occurrence of an item in the inventory
     */
    int first(ItemStack item);

    /**
     * Get the slot if of the first occurrence of an item in the inventory
     *
     * @param type The type of the item to get the slot of
     * @return The slot of the first occurrence of an item in the inventory
     */
    int first(String type);

    /**
     * Get the slot if of the first empty slot in the inventory
     *
     * @return The slot of the first empty slot in the inventory
     */
    int firstEmpty();

    /**
     * Remove an item from the inventory
     *
     * @param item The item to remove
     */
    void remove(ItemStack item);

    /**
     * Remove an item from the inventory
     *
     * @param type The type of the item to remove
     */
    void remove(String type);

    /** Clear the inventory */
    void clear();

    /**
     * Clear a specific slot in the inventory
     *
     * @param slot The slot to clear
     */
    void clear(int slot);
}
