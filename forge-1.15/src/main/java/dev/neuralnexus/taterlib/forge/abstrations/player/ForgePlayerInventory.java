package dev.neuralnexus.taterlib.forge.abstrations.player;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.forge.abstrations.inventory.ForgeInventory;
import dev.neuralnexus.taterlib.forge.abstrations.item.ForgeItemStack;
import net.minecraft.entity.player.PlayerInventory;

/**
 * Abstracts a Forge player inventory to an AbstractPlayerInventory.
 */
public class ForgePlayerInventory extends ForgeInventory implements AbstractPlayerInventory {
    private final PlayerInventory playerInventory;

    /**
     * Constructor.
     * @param playerInventory The Forge player inventory.
     */
    public ForgePlayerInventory(PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getArmorContents() {
        AbstractItemStack[] armorContents = new AbstractItemStack[4];
        for (int i = 0; i < 4; i++) {
            armorContents[i] = new ForgeItemStack(playerInventory.armor.get(i));
        }
        return armorContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getExtraContents() {
        AbstractItemStack[] extraContents = new AbstractItemStack[2];
        for (int i = 0; i < 2; i++) {
            extraContents[i] = new ForgeItemStack(playerInventory.offhand.get(i));
        }
        return extraContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getHelmet() {
        return new ForgeItemStack(playerInventory.armor.get(0));
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getChestplate() {
        return new ForgeItemStack(playerInventory.armor.get(1));
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getLeggings() {
        return new ForgeItemStack(playerInventory.armor.get(2));
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getBoots() {
        return new ForgeItemStack(playerInventory.armor.get(3));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(String type, AbstractItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItem(String type) {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setArmorContents(AbstractItemStack[] items) {
        playerInventory.armor.clear();
        for (int i = 0; i < 4; i++) {
            playerInventory.armor.add(i, ((ForgeItemStack) items[i]).getItemStack());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setExtraContents(AbstractItemStack[] items) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setHelmet(AbstractItemStack item) {
        playerInventory.armor.set(0, ((ForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setChestplate(AbstractItemStack item) {
        playerInventory.armor.set(1, ((ForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLeggings(AbstractItemStack item) {
        playerInventory.armor.set(2, ((ForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setBoots(AbstractItemStack item) {
        playerInventory.armor.set(3, ((ForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItemInMainHand() {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInMainHand(AbstractItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItemInOffHand() {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInOffHand(AbstractItemStack item) {
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
