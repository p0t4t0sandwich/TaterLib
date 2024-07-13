/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_12_2.fabric.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.Inventory;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import java.util.ArrayList;
import java.util.List;

/** Fabric implementation of {@link Inventory}. */
public class FabricInventory implements Inventory {
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
    public int size() {
        return inventory.getInvSize();
    }

    @Override
    public ItemStack get(int slot) {
        return new FabricItemStack(inventory.getInvStack(slot));
    }

    @Override
    public void set(int slot, ItemStack item) {
        inventory.setInvStack(slot, ((FabricItemStack) item).itemStack());
    }

    @Override
    public void add(ItemStack item) {
        for (int i = 0; i < size(); i++) {
            if (get(i).type().equals("minecraft:air")) {
                set(i, item);
                break;
            }
        }
    }

    @Override
    public List<ItemStack> contents() {
        List<ItemStack> contents = new ArrayList<>(size());
        for (int i = 0; i < size(); i++) {
            contents.add(get(i));
        }
        return contents;
    }

    @Override
    public void setContents(List<ItemStack> item) {
        for (int i = 0; i < item.size(); i++) {
            set(i, item.get(i));
        }
    }

    @Override
    public void remove(ResourceKey type) {
        for (int i = 0; i < size(); i++) {
            if (get(i).type().equals(type)) {
                inventory.removeInvStack(i);
            }
        }
    }

    @Override
    public void clear(int slot) {
        inventory.removeInvStack(slot);
    }
}
