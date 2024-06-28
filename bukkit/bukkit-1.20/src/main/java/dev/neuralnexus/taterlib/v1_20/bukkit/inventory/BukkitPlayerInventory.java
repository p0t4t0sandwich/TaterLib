/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20.bukkit.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link PlayerInventory}. */
public class BukkitPlayerInventory extends BukkitInventory implements PlayerInventory {
    private final org.bukkit.inventory.PlayerInventory playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Bukkit player inventory.
     */
    public BukkitPlayerInventory(org.bukkit.inventory.PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> armor() {
        return Arrays.stream(playerInventory.getArmorContents())
                .map(BukkitItemStack::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public void setArmor(List<ItemStack> armor) {
        playerInventory.setArmorContents(
                armor.stream()
                        .map(BukkitItemStack.class::cast)
                        .map(BukkitItemStack::itemStack)
                        .toArray(org.bukkit.inventory.ItemStack[]::new));
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack offhand() {
        return new BukkitItemStack(playerInventory.getItemInOffHand());
    }

    /** {@inheritDoc} */
    @Override
    public void setOffhand(ItemStack offhand) {
        playerInventory.setItemInOffHand(((BukkitItemStack) offhand).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public int selectedSlot() {
        return playerInventory.getHeldItemSlot();
    }
}
