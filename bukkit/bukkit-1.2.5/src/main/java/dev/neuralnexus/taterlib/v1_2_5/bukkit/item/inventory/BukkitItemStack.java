/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_2_5.bukkit.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import org.bukkit.Material;

import java.util.List;
import java.util.Optional;

/** Bukkit implementation of {@link ItemStack}. */
public class BukkitItemStack implements ItemStack, Wrapped<org.bukkit.inventory.ItemStack> {
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

    @Override
    public org.bukkit.inventory.ItemStack unwrap() {
        return this.itemStack;
    }

    @Override
    public ResourceKey type() {
        return ResourceKey.of("minecraft", this.itemStack.getType().name().toLowerCase());
    }

    @Override
    public int count() {
        return this.itemStack.getAmount();
    }

    @Override
    public void setCount(int count) {
        this.itemStack.setAmount(count);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ItemStack clone() {
        return new BukkitItemStack(this.itemStack.clone());
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
        return !itemStack.getEnchantments().isEmpty();
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
