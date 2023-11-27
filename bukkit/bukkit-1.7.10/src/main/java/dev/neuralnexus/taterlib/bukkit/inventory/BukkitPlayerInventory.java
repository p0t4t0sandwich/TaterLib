package dev.neuralnexus.taterlib.bukkit.inventory;

import dev.neuralnexus.taterlib.common.inventory.ItemStack;
import dev.neuralnexus.taterlib.common.inventory.PlayerInventory;

/** Abstracts a Bukkit player inventory to an AbstractPlayerInventory. */
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
    public ItemStack[] getArmorContents() {
        org.bukkit.inventory.ItemStack[] armorContents = playerInventory.getArmorContents();
        ItemStack[] abstractArmorContents = new ItemStack[armorContents.length];
        for (int i = 0; i < armorContents.length; i++) {
            abstractArmorContents[i] =
                    armorContents[i] == null ? null : new BukkitItemStack(armorContents[i]);
        }

        return abstractArmorContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setArmorContents(ItemStack[] items) {
        org.bukkit.inventory.ItemStack[] itemStacks =
                new org.bukkit.inventory.ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            itemStacks[i] = items[i] == null ? null : ((BukkitItemStack) items[i]).getItemStack();
        }

        playerInventory.setArmorContents(itemStacks);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] getExtraContents() {
        //        ItemStack[] extraContents = playerInventory.getExtraContents();
        //        AbstractItemStack[] abstractExtraContents = new
        // AbstractItemStack[extraContents.length];
        //        for (int i = 0; i < extraContents.length; i++) {
        //            abstractExtraContents[i] = extraContents[i] == null ? null : new
        // BukkitItemStack(extraContents[i]);
        //        }
        //
        //        return abstractExtraContents;
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setExtraContents(ItemStack[] items) {
        //        ItemStack[] itemStacks = new ItemStack[items.length];
        //        for (int i = 0; i < items.length; i++) {
        //            itemStacks[i] = items[i] == null ? null : ((BukkitItemStack)
        // items[i]).getItemStack();
        //        }
        //
        //        playerInventory.setExtraContents(itemStacks);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getHelmet() {
        return new BukkitItemStack(playerInventory.getHelmet());
    }

    /** {@inheritDoc} */
    @Override
    public void setHelmet(ItemStack item) {
        playerInventory.setHelmet(((BukkitItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getChestplate() {
        return new BukkitItemStack(playerInventory.getChestplate());
    }

    /** {@inheritDoc} */
    @Override
    public void setChestplate(ItemStack item) {
        playerInventory.setChestplate(((BukkitItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getLeggings() {
        return new BukkitItemStack(playerInventory.getLeggings());
    }

    /** {@inheritDoc} */
    @Override
    public void setLeggings(ItemStack item) {
        playerInventory.setLeggings(((BukkitItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getBoots() {
        return new BukkitItemStack(playerInventory.getBoots());
    }

    /** {@inheritDoc} */
    @Override
    public void setBoots(ItemStack item) {
        playerInventory.setBoots(((BukkitItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(String equipmentSlot, ItemStack item) {
        //        playerInventory.setItem(EquipmentSlot.valueOf(equipmentSlot.toUpperCase()),
        // ((BukkitItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItem(String equipmentSlot) {
        //        return new
        // BukkitItemStack(playerInventory.getItem(EquipmentSlot.valueOf(equipmentSlot.toUpperCase())));
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItemInMainHand() {
        return new BukkitItemStack(playerInventory.getItemInHand());
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInMainHand(ItemStack item) {
        playerInventory.setItemInHand(((BukkitItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItemInOffHand() {
        //        return new BukkitItemStack(playerInventory.getItemInOffHand());
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInOffHand(ItemStack item) {
        //        playerInventory.setItemInOffHand(((BukkitItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public int getHeldItemSlot() {
        return playerInventory.getHeldItemSlot();
    }
}
