/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.item.inventory;

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
    public net.minecraft.world.entity.player.Inventory unwrap() {
        return this.playerInventory;
    }

    @Override
    public List<ItemStack> armor() {
        return this.playerInventory.armor.stream()
                .map(VanillaItemStack::new)
                .collect(Collectors.toList());
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        this.playerInventory.armor.clear();
        armor.stream()
                .map(VanillaItemStack.class::cast)
                .map(VanillaItemStack::unwrap)
                .forEach(this.playerInventory.armor::add);
    }

    @Override
    public ItemStack offhand() {
        return new VanillaItemStack(this.playerInventory.offhand.get(0));
    }

    @Override
    public void setOffhand(ItemStack offhand) {
        this.playerInventory.offhand.clear();
        this.playerInventory.offhand.add(((VanillaItemStack) offhand).unwrap());
    }

    @Override
    public int selectedSlot() {
        return this.playerInventory.selected;
    }
}
