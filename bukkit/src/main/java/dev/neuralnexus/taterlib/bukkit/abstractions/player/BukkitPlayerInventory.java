package dev.neuralnexus.taterlib.bukkit.abstractions.player;

import dev.neuralnexus.taterlib.bukkit.abstractions.inventory.BukkitInventory;
import dev.neuralnexus.taterlib.bukkit.abstractions.item.BukkitItemStack;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class BukkitPlayerInventory extends BukkitInventory implements AbstractPlayerInventory {
    private final PlayerInventory playerInventory;

    public BukkitPlayerInventory(PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    @Override
    public AbstractItemStack[] getArmorContents() {
        ItemStack[] armorContents = playerInventory.getArmorContents();
        AbstractItemStack[] abstractArmorContents = new AbstractItemStack[armorContents.length];
        for (int i = 0; i < armorContents.length; i++) {
            abstractArmorContents[i] = armorContents[i] == null ? null : new BukkitItemStack(armorContents[i]);
        }

        return abstractArmorContents;
    }

    @Override
    public AbstractItemStack[] getExtraContents() {
        ItemStack[] extraContents = playerInventory.getExtraContents();
        AbstractItemStack[] abstractExtraContents = new AbstractItemStack[extraContents.length];
        for (int i = 0; i < extraContents.length; i++) {
            abstractExtraContents[i] = extraContents[i] == null ? null : new BukkitItemStack(extraContents[i]);
        }

        return abstractExtraContents;
    }

    @Override
    public AbstractItemStack getHelmet() {
        return new BukkitItemStack(playerInventory.getHelmet());
    }

    @Override
    public AbstractItemStack getChestplate() {
        return new BukkitItemStack(playerInventory.getChestplate());
    }

    @Override
    public AbstractItemStack getLeggings() {
        return new BukkitItemStack(playerInventory.getLeggings());
    }

    @Override
    public AbstractItemStack getBoots() {
        return new BukkitItemStack(playerInventory.getBoots());
    }

    @Override
    public void setItem(String equipmentSlot, AbstractItemStack abstractItemStack) {
        playerInventory.setItem(EquipmentSlot.valueOf(equipmentSlot.toUpperCase()), ((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public AbstractItemStack getItem(String equipmentSlot) {
        return new BukkitItemStack(playerInventory.getItem(EquipmentSlot.valueOf(equipmentSlot.toUpperCase())));
    }

    @Override
    public void setArmorContents(AbstractItemStack[] abstractItemStacks) {
        ItemStack[] itemStacks = new ItemStack[abstractItemStacks.length];
        for (int i = 0; i < abstractItemStacks.length; i++) {
            itemStacks[i] = abstractItemStacks[i] == null ? null : ((BukkitItemStack) abstractItemStacks[i]).getItemStack();
        }

        playerInventory.setArmorContents(itemStacks);
    }

    @Override
    public void setExtraContents(AbstractItemStack[] abstractItemStacks) {
        ItemStack[] itemStacks = new ItemStack[abstractItemStacks.length];
        for (int i = 0; i < abstractItemStacks.length; i++) {
            itemStacks[i] = abstractItemStacks[i] == null ? null : ((BukkitItemStack) abstractItemStacks[i]).getItemStack();
        }

        playerInventory.setExtraContents(itemStacks);
    }

    @Override
    public void setHelmet(AbstractItemStack abstractItemStack) {
        playerInventory.setHelmet(((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public void setChestplate(AbstractItemStack abstractItemStack) {
        playerInventory.setChestplate(((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public void setLeggings(AbstractItemStack abstractItemStack) {
        playerInventory.setLeggings(((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public void setBoots(AbstractItemStack abstractItemStack) {
        playerInventory.setBoots(((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public AbstractItemStack getItemInMainHand() {
        return new BukkitItemStack(playerInventory.getItemInMainHand());
    }

    @Override
    public void setItemInMainHand(AbstractItemStack abstractItemStack) {
        playerInventory.setItemInMainHand(((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public AbstractItemStack getItemInOffHand() {
        return new BukkitItemStack(playerInventory.getItemInOffHand());
    }

    @Override
    public void setItemInOffHand(AbstractItemStack abstractItemStack) {
        playerInventory.setItemInOffHand(((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public int getHeldItemSlot() {
        return playerInventory.getHeldItemSlot();
    }
}
