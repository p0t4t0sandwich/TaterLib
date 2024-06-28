/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.sponge.inventory;

import dev.neuralnexus.taterlib.inventory.Inventory;
import dev.neuralnexus.taterlib.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/** Sponge implementation of {@link Inventory}. */
public class SpongeInventory implements Inventory {
    private final org.spongepowered.api.item.inventory.Inventory inventory;

    /**
     * Constructor.
     *
     * @param inventory The Sponge inventory.
     */
    public SpongeInventory(org.spongepowered.api.item.inventory.Inventory inventory) {
        this.inventory = inventory;
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return inventory.capacity();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack get(int slot) {
        if (!inventory.peek(slot).isPresent()) {
            return null;
        }
        return new SpongeItemStack(inventory.peek(slot).get());
    }

    /** {@inheritDoc} */
    @Override
    public void set(int slot, ItemStack item) {
        if (inventory.peek(slot).isPresent()) {
            inventory
                    .peek(slot)
                    .get()
                    .setRawData(((SpongeItemStack) item).itemStack().toContainer());
        } else {
            inventory.set(((SpongeItemStack) item).itemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void add(ItemStack item) {
        inventory.offer(((SpongeItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> contents() {
        List<ItemStack> contents = new ArrayList<>(size());
        for (int i = 0; i < size(); i++) {
            contents.add(get(i));
        }
        return contents;
    }

    /** {@inheritDoc} */
    @Override
    public void setContents(List<ItemStack> items) {
        for (int i = 0; i < size(); i++) {
            set(i, items.get(i));
        }
    }

    /** {@inheritDoc} */
    @Override
    public void remove(String type) {
        for (int i = 0; i < size(); i++) {
            if (get(i).type().equals(type)) {
                if (inventory.peek(i).isPresent()) {
                    inventory.peek(i).get().setQuantity(0);
                }
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clear(int slot) {
        if (inventory.peek(slot).isPresent()) {
            inventory.peek(slot).get().setQuantity(0);
        }
    }
}
