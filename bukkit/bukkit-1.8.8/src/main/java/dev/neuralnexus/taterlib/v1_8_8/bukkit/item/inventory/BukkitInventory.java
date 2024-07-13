/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8_8.bukkit.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.Inventory;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link Inventory}. */
public class BukkitInventory implements Inventory {
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
    public int size() {
        return inventory.getSize();
    }

    @Override
    public ItemStack get(int slot) {
        return inventory.getItem(slot) == null
                ? null
                : new BukkitItemStack(inventory.getItem(slot));
    }

    @Override
    public void set(int slot, ItemStack item) {
        inventory.setItem(slot, ((BukkitItemStack) item).itemStack());
    }

    @Override
    public void add(ItemStack item) {
        inventory.addItem(((BukkitItemStack) item).itemStack());
    }

    @Override
    public List<ItemStack> contents() {
        return Arrays.stream(inventory.getContents())
                .map(item -> item == null ? null : new BukkitItemStack(item))
                .collect(Collectors.toList());
    }

    @Override
    public void setContents(List<ItemStack> items) {
        inventory.setContents(
                items.stream()
                        .map(item -> item == null ? null : ((BukkitItemStack) item).itemStack())
                        .toArray(org.bukkit.inventory.ItemStack[]::new));
    }

    @Override
    public void remove(ResourceKey type) {
        inventory.remove(Material.valueOf(type.value().toUpperCase()));
    }

    @Override
    public void clear(int slot) {
        inventory.clear(slot);
    }
}
