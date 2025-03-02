/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.fabric.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.item.inventory.Inventory;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import java.util.ArrayList;
import java.util.List;

/** Fabric implementation of {@link Inventory}. */
public class FabricInventory implements Inventory, Wrapped<net.minecraft.inventory.Inventory> {
    private final net.minecraft.inventory.Inventory inventory;

    /**
     * Constructor.
     *
     * @param inventory The Fabric inventory.
     */
    public FabricInventory(net.minecraft.inventory.Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public net.minecraft.inventory.Inventory unwrap() {
        return this.inventory;
    }

    @Override
    public int size() {
        return this.inventory.getInvSize();
    }

    @Override
    public ItemStack get(int slot) {
        return new FabricItemStack(this.inventory.getInvStack(slot));
    }

    @Override
    public void set(int slot, ItemStack item) {
        this.inventory.setInvStack(slot, ((FabricItemStack) item).unwrap());
    }

    @Override
    public void add(ItemStack item) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).type().asString().equals("minecraft:air")) {
                this.set(i, item);
                break;
            }
        }
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
    public void setContents(List<ItemStack> item) {
        for (int i = 0; i < item.size(); i++) {
            this.set(i, item.get(i));
        }
    }

    @Override
    public void remove(ResourceKey type) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).type().equals(type)) {
                this.inventory.removeInvStack(i);
            }
        }
    }

    @Override
    public void clear(int slot) {
        this.inventory.removeInvStack(slot);
    }
}
