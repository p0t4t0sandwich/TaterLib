/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20.bukkit.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;
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
        Objects.requireNonNull(this.itemStack.getItemMeta());
        return this.itemStack.getItemMeta().hasDisplayName();
    }

    @Override
    @SuppressWarnings("deprecation")
    public Optional<String> displayName() {
        Objects.requireNonNull(this.itemStack.getItemMeta());
        return Optional.of(this.itemStack.getItemMeta().getDisplayName());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setDisplayName(String name) {
        Objects.requireNonNull(this.itemStack.getItemMeta());
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(name);
        this.itemStack.setItemMeta(meta);
    }

    @Override
    public boolean hasLore() {
        Objects.requireNonNull(this.itemStack.getItemMeta());
        return this.itemStack.getItemMeta().hasLore();
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<String> lore() {
        Objects.requireNonNull(this.itemStack.getItemMeta());
        return this.itemStack.getItemMeta().getLore();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setLore(List<String> lore) {
        Objects.requireNonNull(this.itemStack.getItemMeta());
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setLore(lore);
        this.itemStack.setItemMeta(meta);
    }

    @Override
    public boolean hasEnchants() {
        Objects.requireNonNull(this.itemStack.getItemMeta());
        return this.itemStack.getItemMeta().hasEnchants();
    }

    @Override
    public boolean unbreakable() {
        Objects.requireNonNull(this.itemStack.getItemMeta());
        return this.itemStack.getItemMeta().isUnbreakable();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        Objects.requireNonNull(this.itemStack.getItemMeta());
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setUnbreakable(unbreakable);
        this.itemStack.setItemMeta(meta);
    }
}
