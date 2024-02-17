package dev.neuralnexus.taterlib.fabric.inventory;

import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

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
    public ItemStack[] armorContents() {
        ItemStack[] armorContents = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            armorContents[i] = new FabricItemStack(playerInventory.getArmor(i));
        }
        return armorContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setArmorContents(ItemStack[] items) {
        for (int i = 0; i < 4; i++) {
            playerInventory.setInvStack(5 + i, ((FabricItemStack) items[i]).itemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] extraContents() {
        ItemStack[] extraContents = new ItemStack[2];
        extraContents[0] = new FabricItemStack(playerInventory.getMainHandStack());
        try {
            extraContents[1] = new FabricItemStack(playerInventory.getInvStack(45));
        } catch (IndexOutOfBoundsException e) {
            extraContents[1] = null;
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
        return new FabricItemStack(playerInventory.getArmor(0));
    }

    /** {@inheritDoc} */
    @Override
    public void setHelmet(ItemStack item) {
        playerInventory.setInvStack(5, ((FabricItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack chestplate() {
        return new FabricItemStack(playerInventory.getArmor(1));
    }

    /** {@inheritDoc} */
    @Override
    public void setChestplate(ItemStack item) {
        playerInventory.setInvStack(6, ((FabricItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack leggings() {
        return new FabricItemStack(playerInventory.getArmor(2));
    }

    /** {@inheritDoc} */
    @Override
    public void setLeggings(ItemStack item) {
        playerInventory.setInvStack(7, ((FabricItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack boots() {
        return new FabricItemStack(playerInventory.getArmor(3));
    }

    /** {@inheritDoc} */
    @Override
    public void setBoots(ItemStack item) {
        playerInventory.setInvStack(8, ((FabricItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(String equipmentSlot, ItemStack item) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack item(String equipmentSlot) {
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
        return playerInventory.selectedSlot;
    }
}
