/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20_6.vanilla.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterlib.v1_20.vanilla.item.inventory.VanillaItemStack;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;

/** Vanilla implementation of {@link ItemStack}. */
public class VanillaItemStack_1_20_6 extends VanillaItemStack {
    private final net.minecraft.world.item.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The item stack.
     */
    public VanillaItemStack_1_20_6(net.minecraft.world.item.ItemStack itemStack) {
        super(itemStack);
        this.itemStack = itemStack;
    }

    @Override
    public boolean hasDisplayName() {
        return itemStack.get(DataComponents.CUSTOM_NAME) != null;
    }

    @Override
    public void setDisplayName(String name) {
        itemStack.set(DataComponents.CUSTOM_NAME, Component.nullToEmpty(name));
    }
}