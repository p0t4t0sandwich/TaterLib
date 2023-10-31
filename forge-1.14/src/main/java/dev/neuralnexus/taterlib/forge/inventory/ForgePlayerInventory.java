package dev.neuralnexus.taterlib.forge.inventory;

import dev.neuralnexus.taterlib.common.inventory.ItemStack;
import dev.neuralnexus.taterlib.common.inventory.PlayerInventory;

/**
 * Abstracts a Forge player inventory to an AbstractPlayerInventory.
 */
public class ForgePlayerInventory extends ForgeInventory implements PlayerInventory {
    private final net.minecraft.entity.player.PlayerInventory playerInventory;

    /**
     * Constructor.
     * @param playerInventory The Forge player inventory.
     */
    public ForgePlayerInventory(net.minecraft.entity.player.PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack[] getArmorContents() {
        ItemStack[] armorContents = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            armorContents[i] = new ForgeItemStack(playerInventory.armorInventory.get(i));
        }
        return armorContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack[] getExtraContents() {
        ItemStack[] extraContents = new ItemStack[2];
        for (int i = 0; i < 2; i++) {
            extraContents[i] = new ForgeItemStack(playerInventory.offHandInventory.get(i));
        }
        return extraContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getHelmet() {
        return new ForgeItemStack(playerInventory.armorInventory.get(0));
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getChestplate() {
        return new ForgeItemStack(playerInventory.armorInventory.get(1));
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getLeggings() {
        return new ForgeItemStack(playerInventory.armorInventory.get(2));
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getBoots() {
        return new ForgeItemStack(playerInventory.armorInventory.get(3));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(String type, ItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getItem(String type) {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setArmorContents(ItemStack[] items) {
        playerInventory.armorInventory.clear();
        for (int i = 0; i < 4; i++) {
            playerInventory.armorInventory.add(i, ((ForgeItemStack) items[i]).getItemStack());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setExtraContents(ItemStack[] items) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setHelmet(ItemStack item) {
        playerInventory.armorInventory.set(0, ((ForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setChestplate(ItemStack item) {
        playerInventory.armorInventory.set(1, ((ForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLeggings(ItemStack item) {
        playerInventory.armorInventory.set(2, ((ForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setBoots(ItemStack item) {
        playerInventory.armorInventory.set(3, ((ForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getItemInMainHand() {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInMainHand(ItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getItemInOffHand() {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInOffHand(ItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getHeldItemSlot() {
        return playerInventory.currentItem;
    }
}
