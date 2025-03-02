/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.item.inventory.Inventory;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.inventory.IInventory;

import java.util.ArrayList;
import java.util.List;

/** Forge implementation of {@link Inventory}. */
public class ForgeInventory implements Inventory, Wrapped<IInventory> {
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
    public IInventory unwrap() {
        return this.inventory;
    }

    @Override
    public int size() {
        return this.inventory.getSizeInventory();
    }

    @Override
    public ItemStack get(int slot) {
        return new ForgeItemStack(this.inventory.getStackInSlot(slot));
    }

    @Override
    public void set(int slot, ItemStack item) {
        this.inventory.setInventorySlotContents(slot, ((ForgeItemStack) item).unwrap());
    }

    @Override
    public void add(ItemStack item) {
        int firstEmpty = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).type().asString().equals("minecraft:air")) {
                firstEmpty = i;
            }
        }
        if (firstEmpty == -1) return;
        this.set(firstEmpty, item);
    }

    @Override
    public List<ItemStack> contents() {
        List<ItemStack> contents = new ArrayList<>(this.size());
        for (int i = 0; i < this.size(); i++) {
            contents.set(i, this.get(i));
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
                this.inventory.removeStackFromSlot(i);
            }
        }
    }

    @Override
    public void clear(int slot) {
        this.inventory.removeStackFromSlot(slot);
    }
}
