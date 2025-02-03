/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_18.vanilla.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.item.inventory.Inventory;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import java.util.List;
import java.util.stream.Collectors;

/** The Vanilla implementation of {@link Inventory} */
public class VanillaInventory
        implements Inventory, Wrapped<net.minecraft.world.entity.player.Inventory> {
    private final net.minecraft.world.entity.player.Inventory inventory;

    /**
     * Constructor.
     *
     * @param inventory The Forge inventory.
     */
    public VanillaInventory(net.minecraft.world.entity.player.Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public net.minecraft.world.entity.player.Inventory unwrap() {
        return this.inventory;
    }

    @Override
    public int size() {
        return this.inventory.getContainerSize();
    }

    @Override
    public ItemStack get(int slot) {
        return new VanillaItemStack(this.inventory.getItem(slot));
    }

    @Override
    public void set(int slot, ItemStack item) {
        this.inventory.setItem(slot, ((VanillaItemStack) item).unwrap());
    }

    @Override
    public void add(ItemStack item) {
        this.inventory.add(((VanillaItemStack) item).unwrap());
    }

    @Override
    public List<ItemStack> contents() {
        return this.inventory.items.stream()
                .map(VanillaItemStack::new)
                .collect(Collectors.toList());
    }

    @Override
    public void setContents(List<ItemStack> items) {
        this.inventory.items.clear();
        items.stream()
                .map(VanillaItemStack.class::cast)
                .map(VanillaItemStack::unwrap)
                .forEach(this.inventory.items::add);
    }

    @Override
    public void remove(ResourceKey type) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).type().equals(type)) {
                this.inventory.removeItem(((VanillaItemStack) get(i)).unwrap());
            }
        }
    }

    @Override
    public void clear(int slot) {
        this.inventory.removeItem(((VanillaItemStack) get(slot)).unwrap());
    }
}
