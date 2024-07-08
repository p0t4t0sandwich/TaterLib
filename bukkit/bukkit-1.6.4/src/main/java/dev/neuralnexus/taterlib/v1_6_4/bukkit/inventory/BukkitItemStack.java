/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.bukkit.inventory;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.inventory.ItemStack;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/** Abstracts a Bukkit item stack to an AbstractItemStack. */
public class BukkitItemStack implements ItemStack {
    private final org.bukkit.inventory.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Bukkit item stack.
     */
    public BukkitItemStack(org.bukkit.inventory.ItemStack itemStack) {
        this.itemStack =
                itemStack == null ? new org.bukkit.inventory.ItemStack(Material.AIR) : itemStack;
    }

    /**
     * Getter for the Bukkit item stack.
     *
     * @return The Bukkit item stack.
     */
    public org.bukkit.inventory.ItemStack itemStack() {
        return itemStack;
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return "minecraft:" + itemStack.getType().name().toLowerCase();
    }

    /** {@inheritDoc} */
    @Override
    public int count() {
        return itemStack.getAmount();
    }

    /** {@inheritDoc} */
    @Override
    public void setCount(int count) {
        itemStack.setAmount(count);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack clone() {
        return new BukkitItemStack(itemStack.clone());
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasDisplayName() {
        return itemStack.getItemMeta().hasDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public Optional<String> displayName() {
        return itemStack.getItemMeta().getDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public void setDisplayName(String name) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasLore() {
        return itemStack.getItemMeta().hasLore();
    }

    /** {@inheritDoc} */
    @Override
    public List<String> lore() {
        return itemStack.getItemMeta().getLore();
    }

    /** {@inheritDoc} */
    @Override
    public void setLore(List<String> lore) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasEnchants() {
        return itemStack.getItemMeta().hasEnchants();
    }

    /** {@inheritDoc} */
    @Override
    public boolean unbreakable() {
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        throw new VersionFeatureNotSupportedException();
    }
}
