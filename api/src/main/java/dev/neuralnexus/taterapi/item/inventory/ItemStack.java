/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.item.inventory;

import dev.neuralnexus.taterapi.meta.annotations.Range;
import dev.neuralnexus.taterapi.meta.annotations.VersionFeature;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import java.util.List;
import java.util.Optional;

/** The interface for an AbstractItemStack */
public interface ItemStack {
    /**
     * Get the type of the item
     *
     * @return The type of the item
     */
    ResourceKey type();

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
    @VersionFeature(
            name = "ItemStack#hasDisplayName()",
            compatible = @Range(min = MinecraftVersion.V4_2))
    boolean hasDisplayName();

    /**
     * Get the display name of the item
     *
     * @return The display name of the item
     */
    @VersionFeature(
            name = "ItemStack#displayName()",
            compatible = @Range(min = MinecraftVersion.V4_2))
    Optional<String> displayName();

    /**
     * Set the display name of the item
     *
     * @param name The display name of the item
     */
    @VersionFeature(
            name = "ItemStack#setDisplayName()",
            compatible = @Range(min = MinecraftVersion.V4_2))
    void setDisplayName(String name);

    /**
     * Check if the item has lore
     *
     * @return If the item has lore
     */
    @VersionFeature(name = "ItemStack#hasLore()", compatible = @Range(min = MinecraftVersion.V4_2))
    boolean hasLore();

    /**
     * Get the lore of the item
     *
     * @return The lore of the item
     */
    @VersionFeature(name = "ItemStack#lore()", compatible = @Range(min = MinecraftVersion.V4_2))
    List<String> lore();

    /**
     * Set the lore of the item
     *
     * @param lore The lore of the item
     */
    @VersionFeature(
            name = "ItemStack#setLore(List<String>)",
            compatible = @Range(min = MinecraftVersion.V4_2))
    void setLore(List<String> lore);

    /**
     * Check if the item has any enchantments
     *
     * @return If the item has any enchantments
     */
    @VersionFeature(
            name = "ItemStack#hasEnchants()",
            compatible = @Range(min = MinecraftVersion.V1))
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
    @VersionFeature(
            name = "ItemStack#unbreakable()",
            compatible = @Range(min = MinecraftVersion.V7_2))
    boolean unbreakable();

    /**
     * Set if the item is unbreakable
     *
     * @param unbreakable If the item is unbreakable
     */
    @VersionFeature(
            name = "ItemStack#setUnbreakable(boolean)",
            compatible = @Range(min = MinecraftVersion.V7_2))
    void setUnbreakable(boolean unbreakable);
}
