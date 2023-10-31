package dev.neuralnexus.taterlib.common.player;

import dev.neuralnexus.taterlib.common.inventory.AbstractInventory;
import dev.neuralnexus.taterlib.common.inventory.AbstractItemStack;

/**
 * The interface for an AbstractPlayerInventory
 */
public interface AbstractPlayerInventory extends AbstractInventory {
    /**
     * Get the armor contents of the inventory
     * @return The armor contents of the inventory
     */
    AbstractItemStack[] getArmorContents();

    /**
     * Get the extra contents of the inventory
     * @return The extra contents of the inventory
     */
    AbstractItemStack[] getExtraContents();

    /**
     * Get the helmet of the inventory
     * @return The helmet of the inventory
     */
    AbstractItemStack getHelmet();

    /**
     * Get the chestplate of the inventory
     * @return The chestplate of the inventory
     */
    AbstractItemStack getChestplate();

    /**
     * Get the leggings of the inventory
     * @return The leggings of the inventory
     */
    AbstractItemStack getLeggings();

    /**
     * Get the boots of the inventory
     * @return The boots of the inventory
     */
    AbstractItemStack getBoots();

    /**
     * Set an item in a specific equipment slot
     * @param equipmentSlot The equipment slot to set the item in
     * @param item The item to set
     */
    void setItem(String equipmentSlot, AbstractItemStack item);

    /**
     * Get an item in a specific equipment slot
     * @param equipmentSlot The equipment slot to get the item from
     * @return The item in the equipment slot
     */
    AbstractItemStack getItem(String equipmentSlot);

    /**
     * Set the armor contents of the inventory
     */
    void setArmorContents(AbstractItemStack[] items);

    /**
     * Set the extra contents of the inventory
     */
    void setExtraContents(AbstractItemStack[] items);

    /**
     * Set the helmet of the inventory
     * @param item The helmet of the inventory
     */
    void setHelmet(AbstractItemStack item);

    /**
     * Set the chestplate of the inventory
     * @param item The chestplate of the inventory
     */
    void setChestplate(AbstractItemStack item);

    /**
     * Set the leggings of the inventory
     * @param item The leggings of the inventory
     */
    void setLeggings(AbstractItemStack item);

    /**
     * Set the boots of the inventory
     * @param item The boots of the inventory
     */
    void setBoots(AbstractItemStack item);

    /**
     * Get the held item of the inventory
     * @return The held item of the inventory
     */
    AbstractItemStack getItemInMainHand();

    /**
     * Set the held item of the inventory
     * @param item The held item of the inventory
     */
    void setItemInMainHand(AbstractItemStack item);

    /**
     * Get the offhand item of the inventory
     * @return The offhand item of the inventory
     */
    AbstractItemStack getItemInOffHand();

    /**
     * Set the offhand item of the inventory
     * @param item The offhand item of the inventory
     */
    void setItemInOffHand(AbstractItemStack item);

    /**
     * Get the held item slot of the inventory
     * @return The held item slot of the inventory
     */
    int getHeldItemSlot();
}
