/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_11_2.fabric.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;

import java.util.List;
import java.util.stream.Collectors;

/** Fabric implementation of {@link PlayerInventory}. */
public class FabricPlayerInventory extends FabricInventory implements PlayerInventory {
    private final net.minecraft.entity.player.PlayerInventory playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Fabric player inventory.
     */
    public FabricPlayerInventory(net.minecraft.entity.player.PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    @Override
    public List<ItemStack> armor() {
        return playerInventory.field_15083.stream()
                .map(FabricItemStack::new)
                .collect(Collectors.toList());
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        playerInventory.field_15083.clear();
        armor.stream()
                .map(FabricItemStack.class::cast)
                .map(FabricItemStack::itemStack)
                .forEach(playerInventory.field_15083::add);
    }

    @Override
    public ItemStack offhand() {
        return new FabricItemStack(playerInventory.field_15084.get(0));
    }

    @Override
    public void setOffhand(ItemStack offhand) {
        playerInventory.field_15084.set(0, ((FabricItemStack) offhand).itemStack());
    }

    @Override
    public int selectedSlot() {
        return playerInventory.selectedSlot;
    }
}
