/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.fabric.inventory;

import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

import java.util.Arrays;
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

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> armor() {
        return Arrays.stream(playerInventory.armor)
                .map(FabricItemStack::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public void setArmor(List<ItemStack> armor) {
        for (int i = 0; i < playerInventory.armor.length; i++) {
            playerInventory.armor[i] = ((FabricItemStack) armor.get(i)).itemStack();
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack offhand() {
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setOffhand(ItemStack offhand) {
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public int selectedSlot() {
        return playerInventory.selectedSlot;
    }
}
