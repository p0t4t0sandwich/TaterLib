/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.sponge.legacy.item.inventory;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;

import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Sponge implementation of {@link PlayerInventory}. */
public class SpongePlayerInventory extends SpongeInventory implements PlayerInventory {
    private final org.spongepowered.api.item.inventory.entity.PlayerInventory playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Sponge player inventory.
     */
    public SpongePlayerInventory(
            org.spongepowered.api.item.inventory.entity.PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    @Override
    public org.spongepowered.api.item.inventory.entity.PlayerInventory unwrap() {
        return this.playerInventory;
    }

    @Override
    public List<ItemStack> armor() {
        EquipmentInventory armor = this.playerInventory.getEquipment();
        List<ItemStack> armorContents = new ArrayList<>(4);
        Optional<org.spongepowered.api.item.inventory.ItemStack> head =
                armor.peek(EquipmentTypes.HEADWEAR);
        armorContents.set(0, head.map(SpongeItemStack::new).orElse(null));
        Optional<org.spongepowered.api.item.inventory.ItemStack> chest =
                armor.peek(EquipmentTypes.CHESTPLATE);
        armorContents.set(1, chest.map(SpongeItemStack::new).orElse(null));
        Optional<org.spongepowered.api.item.inventory.ItemStack> legs =
                armor.peek(EquipmentTypes.LEGGINGS);
        armorContents.set(2, legs.map(SpongeItemStack::new).orElse(null));
        Optional<org.spongepowered.api.item.inventory.ItemStack> feet =
                armor.peek(EquipmentTypes.BOOTS);
        armorContents.set(3, feet.map(SpongeItemStack::new).orElse(null));
        return armorContents;
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        armor.stream()
                .map(SpongeItemStack.class::cast)
                .map(SpongeItemStack::unwrap)
                .forEach(itemStack -> this.playerInventory.getEquipment().offer(itemStack));
    }

    @Override
    public ItemStack offhand() {
        if (MetaAPI.instance().version().isOlderThan(MinecraftVersions.V9)) {
            throw new VersionFeatureNotSupportedException();
        }
        return new SpongeItemStack(this.playerInventory.getOffhand().first());
    }

    @Override
    public void setOffhand(ItemStack offhand) {
        if (MetaAPI.instance().version().isOlderThan(MinecraftVersions.V9)) {
            throw new VersionFeatureNotSupportedException();
        }
        this.playerInventory.getOffhand().set(((SpongeItemStack) offhand).unwrap());
    }

    @Override
    public int selectedSlot() {
        return this.playerInventory.getHotbar().getSelectedSlotIndex();
    }
}
