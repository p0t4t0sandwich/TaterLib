package dev.neuralnexus.taterlib.fabric.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

/** Abstracts a Fabric player inventory to an AbstractPlayerInventory. */
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
    public ItemStack[] getArmorContents() {
        ItemStack[] armorContents = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            armorContents[i] = new FabricItemStack(playerInventory.armor.get(i));
        }
        return armorContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setArmorContents(ItemStack[] items) {
        playerInventory.armor.clear();
        for (int i = 0; i < 4; i++) {
            playerInventory.armor.add(i, ((FabricItemStack) items[i]).getItemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] getExtraContents() {
        ItemStack[] extraContents = new ItemStack[2];
        for (int i = 0; i < 2; i++) {
            extraContents[i] = new FabricItemStack(playerInventory.offHand.get(i));
        }
        return extraContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setExtraContents(ItemStack[] items) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getHelmet() {
        return new FabricItemStack(playerInventory.armor.get(0));
    }

    /** {@inheritDoc} */
    @Override
    public void setHelmet(ItemStack item) {
        playerInventory.armor.set(0, ((FabricItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getChestplate() {
        return new FabricItemStack(playerInventory.armor.get(1));
    }

    /** {@inheritDoc} */
    @Override
    public void setChestplate(ItemStack item) {
        playerInventory.armor.set(1, ((FabricItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getLeggings() {
        return new FabricItemStack(playerInventory.armor.get(2));
    }

    /** {@inheritDoc} */
    @Override
    public void setLeggings(ItemStack item) {
        playerInventory.armor.set(2, ((FabricItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getBoots() {
        return new FabricItemStack(playerInventory.armor.get(3));
    }

    /** {@inheritDoc} */
    @Override
    public void setBoots(ItemStack item) {
        playerInventory.armor.set(3, ((FabricItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(String equipmentSlot, ItemStack item) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItem(String equipmentSlot) {
        // TODO: Implement
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItemInMainHand() {
        // TODO: Implement
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInMainHand(ItemStack item) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItemInOffHand() {
        // TODO: Implement
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInOffHand(ItemStack item) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public int getHeldItemSlot() {
        return playerInventory.selectedSlot;
    }
}
