package dev.neuralnexus.taterlib.forge.inventory;

import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

import net.minecraft.entity.player.InventoryPlayer;

/** Forge implementation of {@link PlayerInventory}. */
public class ForgePlayerInventory extends ForgeInventory implements PlayerInventory {
    private final InventoryPlayer playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Forge player inventory.
     */
    public ForgePlayerInventory(InventoryPlayer playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] armorContents() {
        ItemStack[] armorContents = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            armorContents[i] = new ForgeItemStack(playerInventory.armorInventory[i]);
        }
        return armorContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setArmorContents(ItemStack[] items) {
        clear();
        for (int i = 0; i < 4; i++) {
            playerInventory.armorInventory[i] = ((ForgeItemStack) items[i]).itemStack();
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] extraContents() {
        ItemStack[] extraContents = new ItemStack[2];
        for (int i = 0; i < 2; i++) {
            extraContents[i] = new ForgeItemStack(playerInventory.offHandInventory[i]);
        }
        return extraContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setExtraContents(ItemStack[] items) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack helmet() {
        return new ForgeItemStack(playerInventory.armorInventory[0]);
    }

    /** {@inheritDoc} */
    @Override
    public void setHelmet(ItemStack item) {
        playerInventory.armorInventory[0] = ((ForgeItemStack) item).itemStack();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack chestplate() {
        return new ForgeItemStack(playerInventory.armorInventory[1]);
    }

    /** {@inheritDoc} */
    @Override
    public void setChestplate(ItemStack item) {
        playerInventory.armorInventory[1] = ((ForgeItemStack) item).itemStack();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack leggings() {
        return new ForgeItemStack(playerInventory.armorInventory[2]);
    }

    /** {@inheritDoc} */
    @Override
    public void setLeggings(ItemStack item) {
        playerInventory.armorInventory[2] = ((ForgeItemStack) item).itemStack();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack boots() {
        return new ForgeItemStack(playerInventory.armorInventory[3]);
    }

    /** {@inheritDoc} */
    @Override
    public void setBoots(ItemStack item) {
        playerInventory.armorInventory[3] = ((ForgeItemStack) item).itemStack();
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(String type, ItemStack item) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack item(String type) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack itemInMainHand() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInMainHand(ItemStack item) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack itemInOffHand() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInOffHand(ItemStack item) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public int heldItemSlot() {
        return playerInventory.currentItem;
    }
}
