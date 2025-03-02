/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.fabric.item.inventory;

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
    public net.minecraft.entity.player.PlayerInventory unwrap() {
        return this.playerInventory;
    }

    @Override
    public List<ItemStack> armor() {
        return this.playerInventory.field_15083.stream()
                .map(FabricItemStack::new)
                .collect(Collectors.toList());
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        this.playerInventory.field_15083.clear();
        armor.stream()
                .map(FabricItemStack.class::cast)
                .map(FabricItemStack::unwrap)
                .forEach(this.playerInventory.field_15083::add);
    }

    @Override
    public ItemStack offhand() {
        return new FabricItemStack(this.playerInventory.field_15084.get(0));
    }

    @Override
    public void setOffhand(ItemStack offhand) {
        this.playerInventory.field_15084.set(0, ((FabricItemStack) offhand).unwrap());
    }

    @Override
    public int selectedSlot() {
        return this.playerInventory.selectedSlot;
    }
}
