/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_19.sponge.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.item.inventory.Inventory;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import java.util.ArrayList;
import java.util.List;

/** Sponge implementation of {@link Inventory}. */
public class SpongeInventory
        implements Inventory, Wrapped<org.spongepowered.api.item.inventory.Inventory> {
    private final org.spongepowered.api.item.inventory.Inventory inventory;

    /**
     * Constructor.
     *
     * @param inventory The Sponge inventory.
     */
    public SpongeInventory(org.spongepowered.api.item.inventory.Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public org.spongepowered.api.item.inventory.Inventory unwrap() {
        return this.inventory;
    }

    @Override
    public int size() {
        return this.inventory.capacity();
    }

    @Override
    public ItemStack get(int slot) {
        if (!this.inventory.slot(slot).isPresent()) {
            return null;
        }
        return new SpongeItemStack(this.inventory.slot(slot).get().peek());
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void set(int slot, ItemStack item) {
        this.inventory.slot(slot).get().set(((SpongeItemStack) item).unwrap());
    }

    @Override
    public void add(ItemStack item) {
        this.inventory.offer(((SpongeItemStack) item).unwrap());
    }

    @Override
    public List<ItemStack> contents() {
        List<ItemStack> contents = new ArrayList<>(this.size());
        for (int i = 0; i < this.size(); i++) {
            contents.add(this.get(i));
        }
        return contents;
    }

    @Override
    public void setContents(List<ItemStack> items) {
        for (int i = 0; i < this.size(); i++) {
            this.set(i, items.get(i));
        }
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void remove(ResourceKey type) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).type().equals(type)) {
                this.inventory.slot(i).get().clear();
            }
        }
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void clear(int slot) {
        this.inventory.slot(slot).get().clear();
    }
}
