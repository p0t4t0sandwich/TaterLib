/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.player.PlayerInventoryBridge;

import java.util.List;

/** Vanilla implementation of {@link PlayerInventory}. */
public class WrappedPlayerInventory extends WrappedInventory implements PlayerInventory {
    private final net.minecraft.entity.player.PlayerInventory playerInventory;

    public WrappedPlayerInventory(net.minecraft.entity.player.PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    @Override
    public net.minecraft.entity.player.PlayerInventory unwrap() {
        return this.playerInventory;
    }

    @Override
    public List<ItemStack> armor() {
        return ((PlayerInventoryBridge) this.playerInventory).bridge$armor();
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        ((PlayerInventoryBridge) this.playerInventory).bridge$setArmor(armor);
    }

    @Override
    public ItemStack offhand() {
        return ((PlayerInventoryBridge) this.playerInventory).bridge$offhand();
    }

    @Override
    public void setOffhand(ItemStack offhand) {
        ((PlayerInventoryBridge) this.playerInventory).bridge$setOffhand(offhand);
    }

    @Override
    public int selectedSlot() {
        return this.playerInventory.selectedSlot;
    }
}
