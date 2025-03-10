/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.item.inventory;

import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.v1_8_9.sponge.SpongeFactories;

import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Sponge implementation of {@link PlayerInventory}. */
public class SpongePlayerInventory extends SpongeInventory implements PlayerInventory {
    private final CarriedInventory<?> playerInventory;

    public SpongePlayerInventory(CarriedInventory<?> playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    @Override
    public List<ItemStack> armor() {
        List<ItemStack> armorContents = new ArrayList<>(4);
        EquipmentInventory equipment =
                SpongeFactories.equipmentFromInventory.get(this.playerInventory);
        Optional<org.spongepowered.api.item.inventory.ItemStack> head =
                equipment.peek(EquipmentTypes.HEADWEAR);
        armorContents.set(0, head.map(SpongeItemStack::new).orElse(null));
        Optional<org.spongepowered.api.item.inventory.ItemStack> chest =
                equipment.peek(EquipmentTypes.CHESTPLATE);
        armorContents.set(1, chest.map(SpongeItemStack::new).orElse(null));
        Optional<org.spongepowered.api.item.inventory.ItemStack> legs =
                equipment.peek(EquipmentTypes.LEGGINGS);
        armorContents.set(2, legs.map(SpongeItemStack::new).orElse(null));
        Optional<org.spongepowered.api.item.inventory.ItemStack> feet =
                equipment.peek(EquipmentTypes.BOOTS);
        armorContents.set(3, feet.map(SpongeItemStack::new).orElse(null));
        return armorContents;
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        EquipmentInventory armorInventory =
                SpongeFactories.equipmentFromInventory.get(this.playerInventory);
        armorInventory.clear();
        armor.stream()
                .map(SpongeItemStack.class::cast)
                .map(SpongeItemStack::unwrap)
                .forEach(armorInventory::offer);
    }

    @Override
    public ItemStack offhand() {
        return SpongeFactories.inventoryOffHand.get(this.playerInventory);
    }

    @Override
    public void setOffhand(ItemStack offhand) {
        SpongeFactories.inventorySetOffHand.set(this.playerInventory, offhand);
    }

    @Override
    public int selectedSlot() {
        return SpongeFactories.inventorySelectedSlot.get(this.playerInventory);
    }
}
