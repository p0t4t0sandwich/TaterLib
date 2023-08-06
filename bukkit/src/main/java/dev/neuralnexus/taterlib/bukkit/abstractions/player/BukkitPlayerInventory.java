package dev.neuralnexus.taterlib.bukkit.abstractions.player;

import dev.neuralnexus.taterlib.bukkit.abstractions.inventory.BukkitInventory;
import dev.neuralnexus.taterlib.bukkit.abstractions.item.BukkitItemStack;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * Abstracts a Bukkit player inventory to an AbstractPlayerInventory.
 */
public class BukkitPlayerInventory extends BukkitInventory implements AbstractPlayerInventory {
    private final PlayerInventory playerInventory;

    /**
     * Constructor.
     * @param playerInventory The Bukkit player inventory.
     */
    public BukkitPlayerInventory(PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getArmorContents() {
        ItemStack[] armorContents = playerInventory.getArmorContents();
        AbstractItemStack[] abstractArmorContents = new AbstractItemStack[armorContents.length];
        for (int i = 0; i < armorContents.length; i++) {
            abstractArmorContents[i] = armorContents[i] == null ? null : new BukkitItemStack(armorContents[i]);
        }

        return abstractArmorContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getExtraContents() {
        ItemStack[] extraContents = playerInventory.getExtraContents();
        AbstractItemStack[] abstractExtraContents = new AbstractItemStack[extraContents.length];
        for (int i = 0; i < extraContents.length; i++) {
            abstractExtraContents[i] = extraContents[i] == null ? null : new BukkitItemStack(extraContents[i]);
        }

        return abstractExtraContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getHelmet() {
        return new BukkitItemStack(playerInventory.getHelmet());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getChestplate() {
        return new BukkitItemStack(playerInventory.getChestplate());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getLeggings() {
        return new BukkitItemStack(playerInventory.getLeggings());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getBoots() {
        return new BukkitItemStack(playerInventory.getBoots());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(String equipmentSlot, AbstractItemStack item) {
        playerInventory.setItem(EquipmentSlot.valueOf(equipmentSlot.toUpperCase()), ((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItem(String equipmentSlot) {
        return new BukkitItemStack(playerInventory.getItem(EquipmentSlot.valueOf(equipmentSlot.toUpperCase())));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setArmorContents(AbstractItemStack[] items) {
        ItemStack[] itemStacks = new ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            itemStacks[i] = items[i] == null ? null : ((BukkitItemStack) items[i]).getItemStack();
        }

        playerInventory.setArmorContents(itemStacks);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setExtraContents(AbstractItemStack[] items) {
        ItemStack[] itemStacks = new ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            itemStacks[i] = items[i] == null ? null : ((BukkitItemStack) items[i]).getItemStack();
        }

        playerInventory.setExtraContents(itemStacks);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setHelmet(AbstractItemStack item) {
        playerInventory.setHelmet(((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setChestplate(AbstractItemStack item) {
        playerInventory.setChestplate(((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLeggings(AbstractItemStack item) {
        playerInventory.setLeggings(((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setBoots(AbstractItemStack item) {
        playerInventory.setBoots(((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItemInMainHand() {
        return new BukkitItemStack(playerInventory.getItemInMainHand());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInMainHand(AbstractItemStack item) {
        playerInventory.setItemInMainHand(((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItemInOffHand() {
        return new BukkitItemStack(playerInventory.getItemInOffHand());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInOffHand(AbstractItemStack item) {
        playerInventory.setItemInOffHand(((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getHeldItemSlot() {
        return playerInventory.getHeldItemSlot();
    }
}
