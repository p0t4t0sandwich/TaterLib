package dev.neuralnexus.taterlib.neoforge.abstractions.player;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.neoforge.abstractions.inventory.NeoForgeInventory;
import dev.neuralnexus.taterlib.neoforge.abstractions.item.NeoForgeItemStack;
import net.minecraft.world.entity.player.Inventory;

/**
 * Abstracts a NeoForge player inventory to an AbstractPlayerInventory.
 */
public class NeoForgePlayerInventory extends NeoForgeInventory implements AbstractPlayerInventory {
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
    public AbstractItemStack[] getArmorContents() {
        AbstractItemStack[] armorContents = new AbstractItemStack[4];
        for (int i = 0; i < 4; i++) {
            armorContents[i] = new NeoForgeItemStack(playerInventory.armor.get(i));
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
            extraContents[i] = new NeoForgeItemStack(playerInventory.offhand.get(i));
        }
        return extraContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getHelmet() {
        return new NeoForgeItemStack(playerInventory.armor.get(0));
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getChestplate() {
        return new NeoForgeItemStack(playerInventory.armor.get(1));
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getLeggings() {
        return new NeoForgeItemStack(playerInventory.armor.get(2));
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getBoots() {
        return new NeoForgeItemStack(playerInventory.armor.get(3));
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
            playerInventory.armor.add(i, ((NeoForgeItemStack) items[i]).getItemStack());
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
        playerInventory.armor.set(0, ((NeoForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setChestplate(AbstractItemStack item) {
        playerInventory.armor.set(1, ((NeoForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLeggings(AbstractItemStack item) {
        playerInventory.armor.set(2, ((NeoForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setBoots(AbstractItemStack item) {
        playerInventory.armor.set(3, ((NeoForgeItemStack) item).getItemStack());
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
