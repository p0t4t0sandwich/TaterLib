/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.b1_7_3.bukkit.item.inventory;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import org.bukkit.Material;

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
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ItemStack clone() {
        return new BukkitItemStack(itemStack.clone());
    }

    @Override
    public boolean hasDisplayName() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public Optional<String> displayName() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setDisplayName(String name) {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public boolean hasLore() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public List<String> lore() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setLore(List<String> lore) {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public boolean hasEnchants() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public boolean unbreakable() {
        // TODO: Planned internal module covering this functionality
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Planned internal module covering this functionality
        throw new VersionFeatureNotSupportedException();
    }
}
