package dev.neuralnexus.taterlib.neoforge.inventory;

import dev.neuralnexus.taterlib.common.inventory.ItemStack;
import dev.neuralnexus.taterlib.common.inventory.PlayerInventory;
import net.minecraft.world.entity.player.Inventory;

/**
 * Abstracts a NeoForge player inventory to an AbstractPlayerInventory.
 */
public class NeoForgePlayerInventory extends NeoForgeInventory implements PlayerInventory {
    private final Inventory playerInventory;

    /**
     * Constructor.
     * @param playerInventory The NeoForge player inventory.
     */
    public NeoForgePlayerInventory(Inventory playerInventory) {
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
            armorContents[i] = new NeoForgeItemStack(playerInventory.armor.get(i));
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
            extraContents[i] = new NeoForgeItemStack(playerInventory.offhand.get(i));
        }
        return extraContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getHelmet() {
        return new NeoForgeItemStack(playerInventory.armor.get(0));
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getChestplate() {
        return new NeoForgeItemStack(playerInventory.armor.get(1));
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getLeggings() {
        return new NeoForgeItemStack(playerInventory.armor.get(2));
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getBoots() {
        return new NeoForgeItemStack(playerInventory.armor.get(3));
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
        playerInventory.armor.clear();
        for (int i = 0; i < 4; i++) {
            playerInventory.armor.add(i, ((NeoForgeItemStack) items[i]).getItemStack());
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
        playerInventory.armor.set(0, ((NeoForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setChestplate(ItemStack item) {
        playerInventory.armor.set(1, ((NeoForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLeggings(ItemStack item) {
        playerInventory.armor.set(2, ((NeoForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setBoots(ItemStack item) {
        playerInventory.armor.set(3, ((NeoForgeItemStack) item).getItemStack());
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
        return playerInventory.selected;
    }
}
