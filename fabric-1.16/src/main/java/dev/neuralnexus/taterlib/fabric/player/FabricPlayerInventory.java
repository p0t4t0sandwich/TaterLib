package dev.neuralnexus.taterlib.fabric.player;

import dev.neuralnexus.taterlib.common.inventory.AbstractItemStack;
import dev.neuralnexus.taterlib.common.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.fabric.inventory.FabricInventory;
import dev.neuralnexus.taterlib.fabric.inventory.FabricItemStack;
import net.minecraft.entity.player.PlayerInventory;

/**
 * Abstracts a Fabric player inventory to an AbstractPlayerInventory.
 */
public class FabricPlayerInventory extends FabricInventory implements AbstractPlayerInventory {
    private final PlayerInventory playerInventory;

    /**
     * Constructor.
     * @param playerInventory The Fabric player inventory.
     */
    public FabricPlayerInventory(PlayerInventory playerInventory) {
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
            armorContents[i] = new FabricItemStack(playerInventory.armor.get(i));
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
            extraContents[i] = new FabricItemStack(playerInventory.offHand.get(i));
        }
        return extraContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getHelmet() {
        return new FabricItemStack(playerInventory.armor.get(0));
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getChestplate() {
        return new FabricItemStack(playerInventory.armor.get(1));
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getLeggings() {
        return new FabricItemStack(playerInventory.armor.get(2));
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getBoots() {
        return new FabricItemStack(playerInventory.armor.get(3));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(String equipmentSlot, AbstractItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItem(String equipmentSlot) {
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
            playerInventory.armor.add(i, ((FabricItemStack) items[i]).getItemStack());
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
        playerInventory.armor.set(0, ((FabricItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setChestplate(AbstractItemStack item) {
        playerInventory.armor.set(1, ((FabricItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLeggings(AbstractItemStack item) {
        playerInventory.armor.set(2, ((FabricItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setBoots(AbstractItemStack item) {
        playerInventory.armor.set(3, ((FabricItemStack) item).getItemStack());
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
        return playerInventory.selectedSlot;
    }
}
