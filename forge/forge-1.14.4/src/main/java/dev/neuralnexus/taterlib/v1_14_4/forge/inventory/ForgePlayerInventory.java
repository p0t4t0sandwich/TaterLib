/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_14_4.forge.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

import java.util.List;
import java.util.stream.Collectors;

/** Forge implementation of {@link PlayerInventory}. */
public class ForgePlayerInventory extends ForgeInventory implements PlayerInventory {
    private final net.minecraft.entity.player.PlayerInventory playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Forge player inventory.
     */
    public ForgePlayerInventory(net.minecraft.entity.player.PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> armor() {
        return playerInventory.armorInventory.stream()
                .map(ForgeItemStack::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public void setArmor(List<ItemStack> armor) {
        playerInventory.armorInventory.clear();
        armor.stream()
                .map(ForgeItemStack.class::cast)
                .map(ForgeItemStack::itemStack)
                .forEach(playerInventory.armorInventory::add);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack offhand() {
        return new ForgeItemStack(playerInventory.offHandInventory.get(0));
    }

    /** {@inheritDoc} */
    @Override
    public void setOffhand(ItemStack offhand) {
        playerInventory.offHandInventory.clear();
        playerInventory.offHandInventory.add(((ForgeItemStack) offhand).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public int selectedSlot() {
        return playerInventory.currentItem;
    }
}
