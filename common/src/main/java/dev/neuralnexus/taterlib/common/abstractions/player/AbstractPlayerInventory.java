package dev.neuralnexus.taterlib.common.abstractions.player;

import dev.neuralnexus.taterlib.common.abstractions.inventory.AbstractInventory;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;

public interface AbstractPlayerInventory extends AbstractInventory {
    AbstractItemStack[] getArmorContents();
    AbstractItemStack[] getExtraContents();
    AbstractItemStack getHelmet();
    AbstractItemStack getChestplate();
    AbstractItemStack getLeggings();
    AbstractItemStack getBoots();
    void setItem(String equipmentSlot, AbstractItemStack item);
    AbstractItemStack getItem(String equipmentSlot);
    void setArmorContents(AbstractItemStack[] items);
    void setExtraContents(AbstractItemStack[] items);
    void setHelmet(AbstractItemStack item);
    void setChestplate(AbstractItemStack item);
    void setLeggings(AbstractItemStack item);
    void setBoots(AbstractItemStack item);
    AbstractItemStack getItemInMainHand();
    void setItemInMainHand(AbstractItemStack item);
    AbstractItemStack getItemInOffHand();
    void setItemInOffHand(AbstractItemStack item);
    int getHeldItemSlot();
}
