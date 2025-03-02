/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.forge.item.inventory;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;

import net.minecraft.entity.player.InventoryPlayer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Forge implementation of {@link PlayerInventory}. */
public class ForgePlayerInventory extends ForgeInventory implements PlayerInventory {
    private final InventoryPlayer playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Forge player inventory.
     */
    public ForgePlayerInventory(InventoryPlayer playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    @Override
    public InventoryPlayer unwrap() {
        return this.playerInventory;
    }

    @Override
    public List<ItemStack> armor() {
        return Arrays.stream(this.playerInventory.armorInventory)
                .map(ForgeItemStack::new)
                .collect(Collectors.toList());
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        for (int i = 0; i < this.playerInventory.armorInventory.length; i++) {
            this.playerInventory.armorInventory[i] = ((ForgeItemStack) armor.get(i)).unwrap();
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
        return this.playerInventory.currentItem;
    }
}
