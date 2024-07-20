/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.forge.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.Inventory;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.inventory.IInventory;

import java.util.ArrayList;
import java.util.List;

/** Forge implementation of {@link Inventory}. */
public class ForgeInventory implements Inventory {
    private final IInventory inventory;

    /**
     * Constructor.
     *
     * @param inventory The Forge inventory.
     */
    public ForgeInventory(IInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public int size() {
        return inventory.getSizeInventory();
    }

    @Override
    public ItemStack get(int slot) {
        return new ForgeItemStack(inventory.getStackInSlot(slot));
    }

    @Override
    public void set(int slot, ItemStack item) {
        inventory.setInventorySlotContents(slot, ((ForgeItemStack) item).itemStack());
    }

    @Override
    public void add(ItemStack item) {
        int firstEmpty = -1;
        for (int i = 0; i < size(); i++) {
            if (get(i).type().equals("minecraft:air")) {
                firstEmpty = i;
            }
        }
        if (firstEmpty == -1) return;
        set(firstEmpty, item);
    }

    @Override
    public List<ItemStack> contents() {
        List<ItemStack> contents = new ArrayList<>(size());
        for (int i = 0; i < size(); i++) {
            contents.set(i, get(i));
        }
        return contents;
    }

    @Override
    public void setContents(List<ItemStack> items) {
        for (int i = 0; i < size(); i++) {
            set(i, items.get(i));
        }
    }

    @Override
    public void remove(ResourceKey type) {
        for (int i = 0; i < size(); i++) {
            if (get(i).type().equals(type)) {
                inventory.removeStackFromSlot(i);
            }
        }
    }

    @Override
    public void clear(int slot) {
        inventory.removeStackFromSlot(slot);
    }
}
