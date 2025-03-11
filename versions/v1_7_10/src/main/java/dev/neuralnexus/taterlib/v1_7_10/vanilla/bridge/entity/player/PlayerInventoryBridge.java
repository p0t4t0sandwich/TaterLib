/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.player;

import dev.neuralnexus.taterapi.item.inventory.ItemStack;

import java.util.List;

public interface PlayerInventoryBridge {
    List<ItemStack> bridge$armor();

    void bridge$setArmor(List<ItemStack> armor);

    ItemStack bridge$offhand();

    void bridge$setOffhand(ItemStack offhand);
}
