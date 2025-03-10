/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.item.inventory;

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
        if (!this.inventory.peek(slot).isPresent()) {
            return null;
        }
        return new SpongeItemStack(this.inventory.peek(slot).get());
    }

    @Override
    public void set(int slot, ItemStack item) {
        if (this.inventory.peek(slot).isPresent()) {
            this.inventory
                    .peek(slot)
                    .get()
                    .setRawData(((SpongeItemStack) item).unwrap().toContainer());
        } else {
            this.inventory.set(((SpongeItemStack) item).unwrap());
        }
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
    public void remove(ResourceKey type) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).type().equals(type)) {
                if (this.inventory.peek(i).isPresent()) {
                    this.inventory.peek(i).get().setQuantity(0);
                }
            }
        }
    }

    @Override
    public void clear(int slot) {
        if (this.inventory.peek(slot).isPresent()) {
            this.inventory.peek(slot).get().setQuantity(0);
        }
    }
}
