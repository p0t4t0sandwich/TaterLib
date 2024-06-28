/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.bukkit.inventory;

import dev.neuralnexus.taterlib.inventory.Inventory;
import dev.neuralnexus.taterlib.inventory.ItemStack;

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

    /** {@inheritDoc} */
    @Override
    public int size() {
        return inventory.getSize();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack get(int slot) {
        return inventory.getItem(slot) == null
                ? null
                : new BukkitItemStack(inventory.getItem(slot));
    }

    /** {@inheritDoc} */
    @Override
    public void set(int slot, ItemStack item) {
        inventory.setItem(slot, ((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void add(ItemStack item) {
        inventory.addItem(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> contents() {
        return Arrays.stream(inventory.getContents())
                .map(item -> item == null ? null : new BukkitItemStack(item))
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public void setContents(List<ItemStack> items) {
        inventory.setContents(
                items.stream()
                        .map(item -> item == null ? null : ((BukkitItemStack) item).itemStack())
                        .toArray(org.bukkit.inventory.ItemStack[]::new));
    }

    /** {@inheritDoc} */
    @Override
    public void remove(String type) {
        if (type.contains(":")) {
            type = type.split(":")[1];
        }
        inventory.remove(Material.valueOf(type.toUpperCase()));
    }

    /** {@inheritDoc} */
    @Override
    public void clear(int slot) {
        inventory.clear(slot);
    }
}
