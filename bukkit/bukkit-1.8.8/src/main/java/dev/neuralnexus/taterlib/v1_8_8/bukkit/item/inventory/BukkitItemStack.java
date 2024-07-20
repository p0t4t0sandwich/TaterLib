/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_8.bukkit.item.inventory;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Optional;

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

    @Override
    public ResourceKey type() {
        return ResourceKey.of("minecraft", itemStack.getType().name().toLowerCase());
    }

    @Override
    public int count() {
        return itemStack.getAmount();
    }

    @Override
    public void setCount(int count) {
        itemStack.setAmount(count);
    }

    @Override
    public ItemStack clone() {
        return new BukkitItemStack(itemStack.clone());
    }

    @Override
    public boolean hasDisplayName() {
        return itemStack.getItemMeta().hasDisplayName();
    }

    @Override
    public Optional<String> displayName() {
        return Optional.of(itemStack.getItemMeta().getDisplayName());
    }

    @Override
    public void setDisplayName(String name) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
    }

    @Override
    public boolean hasLore() {
        return itemStack.getItemMeta().hasLore();
    }

    @Override
    public List<String> lore() {
        return itemStack.getItemMeta().getLore();
    }

    @Override
    public void setLore(List<String> lore) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }

    @Override
    public boolean hasEnchants() {
        return itemStack.getItemMeta().hasEnchants();
    }

    @Override
    public boolean unbreakable() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        throw new VersionFeatureNotSupportedException();
    }
}
