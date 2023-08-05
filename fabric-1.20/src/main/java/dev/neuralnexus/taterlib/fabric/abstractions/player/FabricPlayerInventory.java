package dev.neuralnexus.taterlib.fabric.abstractions.player;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.fabric.abstractions.inventory.FabricInventory;
import dev.neuralnexus.taterlib.fabric.abstractions.item.FabricItemStack;
import net.minecraft.entity.player.PlayerInventory;

public class FabricPlayerInventory extends FabricInventory implements AbstractPlayerInventory {
    private final PlayerInventory playerInventory;

    public FabricPlayerInventory(PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    @Override
    public AbstractItemStack[] getArmorContents() {
        AbstractItemStack[] armorContents = new AbstractItemStack[4];
        for (int i = 0; i < 4; i++) {
            armorContents[i] = new FabricItemStack(playerInventory.armor.get(i));
        }
        return armorContents;
    }

    @Override
    public AbstractItemStack[] getExtraContents() {
        AbstractItemStack[] extraContents = new AbstractItemStack[2];
        for (int i = 0; i < 2; i++) {
            extraContents[i] = new FabricItemStack(playerInventory.offHand.get(i));
        }
        return extraContents;
    }

    @Override
    public AbstractItemStack getHelmet() {
        return new FabricItemStack(playerInventory.armor.get(0));
    }

    @Override
    public AbstractItemStack getChestplate() {
        return new FabricItemStack(playerInventory.armor.get(1));
    }

    @Override
    public AbstractItemStack getLeggings() {
        return new FabricItemStack(playerInventory.armor.get(2));
    }

    @Override
    public AbstractItemStack getBoots() {
        return new FabricItemStack(playerInventory.armor.get(3));
    }

    @Override
    public void setItem(String equipmentSlot, AbstractItemStack item) {
        // TODO: Implement
    }

    @Override
    public AbstractItemStack getItem(String equipmentSlot) {
        // TODO: Implement
        return null;
    }

    @Override
    public void setArmorContents(AbstractItemStack[] items) {
        playerInventory.armor.clear();
        for (int i = 0; i < 4; i++) {
            playerInventory.armor.add(i, ((FabricItemStack) items[i]).getItemStack());
        }
    }

    @Override
    public void setExtraContents(AbstractItemStack[] items) {
        // TODO: Implement
    }

    @Override
    public void setHelmet(AbstractItemStack item) {
        playerInventory.armor.set(0, ((FabricItemStack) item).getItemStack());
    }

    @Override
    public void setChestplate(AbstractItemStack item) {
        playerInventory.armor.set(1, ((FabricItemStack) item).getItemStack());
    }

    @Override
    public void setLeggings(AbstractItemStack item) {
        playerInventory.armor.set(2, ((FabricItemStack) item).getItemStack());
    }

    @Override
    public void setBoots(AbstractItemStack item) {
        playerInventory.armor.set(3, ((FabricItemStack) item).getItemStack());
    }

    @Override
    public AbstractItemStack getItemInMainHand() {
        // TODO: Implement
        return null;
    }

    @Override
    public void setItemInMainHand(AbstractItemStack item) {
        // TODO: Implement
    }

    @Override
    public AbstractItemStack getItemInOffHand() {
        // TODO: Implement
        return null;
    }

    @Override
    public void setItemInOffHand(AbstractItemStack item) {
        // TODO: Implement
    }

    @Override
    public int getHeldItemSlot() {
        return playerInventory.selectedSlot;
    }
}
