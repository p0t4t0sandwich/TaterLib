/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.item.inventory;

import java.util.List;

/** The interface for an AbstractPlayerInventory */
public interface PlayerInventory extends Inventory {
    /**
     * Get the player's items
     *
     * @return The player's items
     */
    default List<ItemStack> items() {
        return contents();
    }

    /**
     * Set the player's items
     *
     * @param items The items to set
     */
    default void setItems(List<ItemStack> items) {
        setContents(items);
    }

    /**
     * Get the player's armor
     *
     * @return The player's armor
     */
    List<ItemStack> armor();

    /**
     * Set the player's armor
     *
     * @param armor The armor to set
     */
    void setArmor(List<ItemStack> armor);

    /**
     * Get the player's helmet
     *
     * @return The player's helmet
     */
    default ItemStack helmet() {
        return armor().get(3);
    }

    /**
     * Set the player's helmet
     *
     * @param helmet The helmet to set
     */
    default void setHelmet(ItemStack helmet) {
        List<ItemStack> armor = armor();
        armor.set(3, helmet);
        setArmor(armor);
    }

    /**
     * Get the player's chestplate
     *
     * @return The player's chestplate
     */
    default ItemStack chestplate() {
        return armor().get(2);
    }

    /**
     * Set the player's chestplate
     *
     * @param chestplate The chestplate to set
     */
    default void setChestplate(ItemStack chestplate) {
        List<ItemStack> armor = armor();
        armor.set(2, chestplate);
        setArmor(armor);
    }

    /**
     * Get the player's leggings
     *
     * @return The player's leggings
     */
    default ItemStack leggings() {
        return armor().get(1);
    }

    /**
     * Set the player's leggings
     *
     * @param leggings The leggings to set
     */
    default void setLeggings(ItemStack leggings) {
        List<ItemStack> armor = armor();
        armor.set(1, leggings);
        setArmor(armor);
    }

    /**
     * Get the player's boots
     *
     * @return The player's boots
     */
    default ItemStack boots() {
        return armor().get(0);
    }

    /**
     * Get the player's offhand
     *
     * @return The player's offhand
     */
    ItemStack offhand();

    /**
     * Set the player's offhand
     *
     * @param offhand The offhand to set
     */
    void setOffhand(ItemStack offhand);

    /**
     * Get the player's selected slot
     *
     * @return The player's selected slot
     */
    int selectedSlot();

    /**
     * Get the player's main hand
     *
     * @return The player's main hand
     */
    default ItemStack mainHand() {
        return get(selectedSlot());
    }

    /**
     * Set the item in the player's main hand
     *
     * @param item The item to set
     */
    default void setMainHand(ItemStack item) {
        set(selectedSlot(), item);
    }
}
