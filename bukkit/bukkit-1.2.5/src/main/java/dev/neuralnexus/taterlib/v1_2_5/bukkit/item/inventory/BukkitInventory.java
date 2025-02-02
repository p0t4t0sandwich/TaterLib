/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_2_5.bukkit.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.item.inventory.Inventory;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import org.bukkit.Material;

import java.util.*;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link Inventory}. */
public class BukkitInventory implements Inventory, Wrapped<org.bukkit.inventory.Inventory> {
    private final org.bukkit.inventory.Inventory inventory;

    /**
     * Constructor.
     *
     * @param inventory The Bukkit inventory.
     */
    public BukkitInventory(org.bukkit.inventory.Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public org.bukkit.inventory.Inventory unwrap() {
        return this.inventory;
    }

    @Override
    public int size() {
        return this.inventory.getSize();
    }

    @Override
    public ItemStack get(int slot) {
        return this.inventory.getItem(slot) == null
                ? null
                : new BukkitItemStack(this.inventory.getItem(slot));
    }

    @Override
    public void set(int slot, ItemStack item) {
        this.inventory.setItem(slot, ((BukkitItemStack) item).unwrap());
    }

    @Override
    public void add(ItemStack item) {
        this.inventory.addItem(((BukkitItemStack) item).unwrap());
    }

    @Override
    public List<ItemStack> contents() {
        return Arrays.stream(this.inventory.getContents())
                .map(item -> item == null ? null : new BukkitItemStack(item))
                .collect(Collectors.toList());
    }

    @Override
    public void setContents(List<ItemStack> items) {
        this.inventory.setContents(
                items.stream()
                        .map(item -> item == null ? null : ((BukkitItemStack) item).unwrap())
                        .toArray(org.bukkit.inventory.ItemStack[]::new));
    }

    @Override
    public void remove(ResourceKey type) {
        this.inventory.remove(Material.valueOf(type.value().toUpperCase()));
    }

    @Override
    public void clear(int slot) {
        this.inventory.clear(slot);
    }
}
