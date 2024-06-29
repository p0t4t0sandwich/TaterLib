/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.inventory;

import java.util.List;

/** The interface for an AbstractItemStack */
public interface ItemStack {
    /**
     * Get the type of the item
     *
     * @return The type of the item
     */
    String type();

    /**
     * Get the amount of the item
     *
     * @return The amount of the item
     */
    int count();

    /**
     * Get the amount of the item
     *
     * @return The amount of the item
     */
    default int amount() {
        return count();
    }

    /**
     * Set the amount of the item
     *
     * @param count The amount of the item
     */
    void setCount(int count);

    /**
     * Clone the item
     *
     * @return The cloned item
     */
    ItemStack clone();

    /**
     * Check if the item has a display name
     *
     * @return If the item has a display name
     */
    boolean hasDisplayName();

    /**
     * Get the display name of the item <br>
     * Unavailable prior to 1.4.2
     *
     * @return The display name of the item
     */
    String displayName();

    /**
     * Set the display name of the item <br>
     * Unavailable prior to 1.4.2
     *
     * @param name The display name of the item
     */
    void setDisplayName(String name);

    /**
     * Check if the item has lore<br>
     * Unavailable prior to 1.4.2
     *
     * @return If the item has lore
     */
    boolean hasLore();

    /**
     * Get the lore of the item<br>
     * Unavailable prior to 1.4.2
     *
     * @return The lore of the item
     */
    List<String> lore();

    /**
     * Set the lore of the item<br>
     * Unavailable prior to 1.4.2
     *
     * @param lore The lore of the item
     */
    void setLore(List<String> lore);

    /**
     * Check if the item has any enchantments <br>
     * Unavailable prior to 1.0.0
     *
     * @return If the item has any enchantments
     */
    boolean hasEnchants();

    // hasEnchant(Enchantment ench)
    // getEnchantLevel(Enchantment ench)
    // getEnchants() -> Map<Enchantment, Integer>
    // addEnchant(Enchantment ench, int level, boolean ignoreRestrictions)
    // removeEnchant(Enchantment ench)

    /**
     * Check if the item is unbreakable<br>
     * Unavailable prior to 1.9
     *
     * @return If the item is unbreakable
     */
    boolean unbreakable();

    /**
     * Set if the item is unbreakable<br>
     * Unavailable on Bukkit prior to 1.9, unavailable everywhere unless implemented
     *
     * @param unbreakable If the item is unbreakable
     */
    void setUnbreakable(boolean unbreakable);
}
