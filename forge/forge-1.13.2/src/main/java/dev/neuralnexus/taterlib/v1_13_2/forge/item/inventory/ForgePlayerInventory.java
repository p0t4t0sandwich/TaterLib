/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;

import net.minecraft.entity.player.InventoryPlayer;

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
        return this.playerInventory.armorInventory.stream()
                .map(ForgeItemStack::new)
                .collect(Collectors.toList());
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        this.playerInventory.armorInventory.clear();
        armor.stream()
                .map(ForgeItemStack.class::cast)
                .map(ForgeItemStack::unwrap)
                .forEach(this.playerInventory.armorInventory::add);
    }

    @Override
    public ItemStack offhand() {
        return new ForgeItemStack(this.playerInventory.offHandInventory.get(0));
    }

    @Override
    public void setOffhand(ItemStack offhand) {
        this.playerInventory.offHandInventory.clear();
        this.playerInventory.offHandInventory.add(((ForgeItemStack) offhand).unwrap());
    }

    @Override
    public int selectedSlot() {
        return this.playerInventory.currentItem;
    }
}
