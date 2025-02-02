/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.bukkit.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;

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

    @Override
    public org.bukkit.inventory.PlayerInventory unwrap() {
        return this.playerInventory;
    }

    @Override
    public List<ItemStack> armor() {
        return Arrays.stream(this.playerInventory.getArmorContents())
                .map(BukkitItemStack::new)
                .collect(Collectors.toList());
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        this.playerInventory.setArmorContents(
                armor.stream()
                        .map(BukkitItemStack.class::cast)
                        .map(BukkitItemStack::unwrap)
                        .toArray(org.bukkit.inventory.ItemStack[]::new));
    }

    @Override
    public ItemStack offhand() {
        return new BukkitItemStack(this.playerInventory.getItemInOffHand());
    }

    @Override
    public void setOffhand(ItemStack offhand) {
        this.playerInventory.setItemInOffHand(((BukkitItemStack) offhand).unwrap());
    }

    @Override
    public int selectedSlot() {
        return this.playerInventory.getHeldItemSlot();
    }
}
