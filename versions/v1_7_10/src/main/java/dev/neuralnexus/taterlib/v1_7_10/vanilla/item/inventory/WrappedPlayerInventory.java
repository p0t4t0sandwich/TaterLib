/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.item.inventory;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return Arrays.stream(this.playerInventory.armorSlots)
                .map(WrappedItemStack::new)
                .collect(Collectors.toList());
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        for (int i = 0; i < this.playerInventory.armorSlots.length; i++) {
            this.playerInventory.armorSlots[i] = ((WrappedItemStack) armor.get(i)).unwrap();
        }
    }

    @Override
    public ItemStack offhand() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setOffhand(ItemStack offhand) {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public int selectedSlot() {
        return this.playerInventory.selectedSlot;
    }
}
