/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_21_4.vanilla.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.Inventory;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import java.util.List;
import java.util.stream.Collectors;

/** The Vanilla implementation of {@link Inventory} */
public class VanillaInventory implements Inventory {
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
    public int size() {
        return inventory.getContainerSize();
    }

    @Override
    public ItemStack get(int slot) {
        return new VanillaItemStack(inventory.getItem(slot));
    }

    @Override
    public void set(int slot, ItemStack item) {
        inventory.setItem(slot, ((VanillaItemStack) item).itemStack());
    }

    @Override
    public void add(ItemStack item) {
        inventory.add(((VanillaItemStack) item).itemStack());
    }

    @Override
    public List<ItemStack> contents() {
        return inventory.items.stream().map(VanillaItemStack::new).collect(Collectors.toList());
    }

    @Override
    public void setContents(List<ItemStack> items) {
        inventory.items.clear();
        items.stream()
                .map(VanillaItemStack.class::cast)
                .map(VanillaItemStack::itemStack)
                .forEach(inventory.items::add);
    }

    @Override
    public void remove(ResourceKey type) {
        for (int i = 0; i < size(); i++) {
            if (get(i).type().equals(type)) {
                inventory.removeItem(((VanillaItemStack) get(i)).itemStack());
            }
        }
    }

    @Override
    public void clear(int slot) {
        inventory.removeItem(((VanillaItemStack) get(slot)).itemStack());
    }
}
