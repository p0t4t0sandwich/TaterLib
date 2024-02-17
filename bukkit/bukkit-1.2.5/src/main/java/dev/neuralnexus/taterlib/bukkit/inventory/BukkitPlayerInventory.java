package dev.neuralnexus.taterlib.bukkit.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

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
    public ItemStack[] armorContents() {
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
            itemStacks[i] = items[i] == null ? null : ((BukkitItemStack) items[i]).itemStack();
        }

        playerInventory.setArmorContents(itemStacks);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] extraContents() {
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
        // items[i]).itemStack();
        //        }
        //
        //        playerInventory.setExtraContents(itemStacks);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack helmet() {
        return new BukkitItemStack(playerInventory.getHelmet());
    }

    /** {@inheritDoc} */
    @Override
    public void setHelmet(ItemStack item) {
        playerInventory.setHelmet(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack chestplate() {
        return new BukkitItemStack(playerInventory.getChestplate());
    }

    /** {@inheritDoc} */
    @Override
    public void setChestplate(ItemStack item) {
        playerInventory.setChestplate(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack leggings() {
        return new BukkitItemStack(playerInventory.getLeggings());
    }

    /** {@inheritDoc} */
    @Override
    public void setLeggings(ItemStack item) {
        playerInventory.setLeggings(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack boots() {
        return new BukkitItemStack(playerInventory.getBoots());
    }

    /** {@inheritDoc} */
    @Override
    public void setBoots(ItemStack item) {
        playerInventory.setBoots(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(String equipmentSlot, ItemStack item) {
        //        playerInventory.setItem(EquipmentSlot.valueOf(equipmentSlot.toUpperCase()),
        // ((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack item(String equipmentSlot) {
        //        return new
        // BukkitItemStack(playerInventory.getItem(EquipmentSlot.valueOf(equipmentSlot.toUpperCase())));
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack itemInMainHand() {
        return new BukkitItemStack(playerInventory.getItemInHand());
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInMainHand(ItemStack item) {
        playerInventory.setItemInHand(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack itemInOffHand() {
        //        return new BukkitItemStack(playerInventory.getItemInOffHand());
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInOffHand(ItemStack item) {
        //        playerInventory.setItemInOffHand(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public int heldItemSlot() {
        return playerInventory.getHeldItemSlot();
    }
}
