package dev.neuralnexus.taterlib.inventory;

/** The interface for an AbstractPlayerInventory */
public interface PlayerInventory extends Inventory {
    /**
     * Get the armor contents of the inventory
     *
     * @return The armor contents of the inventory
     */
    ItemStack[] getArmorContents();

    /** Set the armor contents of the inventory */
    void setArmorContents(ItemStack[] items);

    /**
     * Get the extra contents of the inventory
     *
     * @return The extra contents of the inventory
     */
    ItemStack[] getExtraContents();

    /** Set the extra contents of the inventory */
    void setExtraContents(ItemStack[] items);

    /**
     * Get the helmet of the inventory
     *
     * @return The helmet of the inventory
     */
    ItemStack getHelmet();

    /**
     * Set the helmet of the inventory
     *
     * @param item The helmet of the inventory
     */
    void setHelmet(ItemStack item);

    /**
     * Get the chestplate of the inventory
     *
     * @return The chestplate of the inventory
     */
    ItemStack getChestplate();

    /**
     * Set the chestplate of the inventory
     *
     * @param item The chestplate of the inventory
     */
    void setChestplate(ItemStack item);

    /**
     * Get the leggings of the inventory
     *
     * @return The leggings of the inventory
     */
    ItemStack getLeggings();

    /**
     * Set the leggings of the inventory
     *
     * @param item The leggings of the inventory
     */
    void setLeggings(ItemStack item);

    /**
     * Get the boots of the inventory
     *
     * @return The boots of the inventory
     */
    ItemStack getBoots();

    /**
     * Set the boots of the inventory
     *
     * @param item The boots of the inventory
     */
    void setBoots(ItemStack item);

    /**
     * Set an item in a specific equipment slot
     *
     * @param equipmentSlot The equipment slot to set the item in
     * @param item The item to set
     */
    void setItem(String equipmentSlot, ItemStack item);

    /**
     * Get an item in a specific equipment slot
     *
     * @param equipmentSlot The equipment slot to get the item from
     * @return The item in the equipment slot
     */
    ItemStack getItem(String equipmentSlot);

    /**
     * Get the held item of the inventory
     *
     * @return The held item of the inventory
     */
    ItemStack getItemInMainHand();

    /**
     * Set the held item of the inventory
     *
     * @param item The held item of the inventory
     */
    void setItemInMainHand(ItemStack item);

    /**
     * Get the offhand item of the inventory
     *
     * @return The offhand item of the inventory
     */
    ItemStack getItemInOffHand();

    /**
     * Set the offhand item of the inventory
     *
     * @param item The offhand item of the inventory
     */
    void setItemInOffHand(ItemStack item);

    /**
     * Get the held item slot of the inventory
     *
     * @return The held item slot of the inventory
     */
    int getHeldItemSlot();
}
