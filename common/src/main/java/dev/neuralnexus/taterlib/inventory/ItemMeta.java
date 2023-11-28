package dev.neuralnexus.taterlib.inventory;

import java.util.List;

/** The interface for an AbstractItemMeta */
public interface ItemMeta {
    /**
     * Check if the item has a display name
     *
     * @return If the item has a display name
     */
    boolean hasDisplayName();

    /**
     * Get the display name of the item
     *
     * @return The display name of the item
     */
    String getDisplayName();

    /**
     * Set the display name of the item
     *
     * @param name The display name of the item
     */
    void setDisplayName(String name);

    /**
     * Check if the item has lore
     *
     * @return If the item has lore
     */
    boolean hasLore();

    /**
     * Get the lore of the item
     *
     * @return The lore of the item
     */
    List<String> getLore();

    /**
     * Set the lore of the item
     *
     * @param lore The lore of the item
     */
    void setLore(List<String> lore);

    /**
     * Check if the item has any enchantments
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
     * Check if the item is unbreakable
     *
     * @return If the item is unbreakable
     */
    boolean isUnbreakable();

    /**
     * Set if the item is unbreakable
     *
     * @param unbreakable If the item is unbreakable
     */
    void setUnbreakable(boolean unbreakable);
}
