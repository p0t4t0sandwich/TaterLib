/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20.vanilla.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;

import net.minecraft.world.entity.player.Inventory;

import java.util.List;
import java.util.stream.Collectors;

/** Vanilla implementation of {@link PlayerInventory} */
public class VanillaPlayerInventory extends VanillaInventory implements PlayerInventory {
    private final Inventory playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Forge player inventory.
     */
    public VanillaPlayerInventory(Inventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    @Override
    public List<ItemStack> armor() {
        return playerInventory.armor.stream()
                .map(VanillaItemStack::new)
                .collect(Collectors.toList());
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        playerInventory.armor.clear();
        armor.stream()
                .map(VanillaItemStack.class::cast)
                .map(VanillaItemStack::itemStack)
                .forEach(playerInventory.armor::add);
    }

    @Override
    public ItemStack offhand() {
        return new VanillaItemStack(playerInventory.offhand.get(0));
    }

    @Override
    public void setOffhand(ItemStack offhand) {
        playerInventory.offhand.clear();
        playerInventory.offhand.add(((VanillaItemStack) offhand).itemStack());
    }

    @Override
    public int selectedSlot() {
        return playerInventory.selected;
    }
}
