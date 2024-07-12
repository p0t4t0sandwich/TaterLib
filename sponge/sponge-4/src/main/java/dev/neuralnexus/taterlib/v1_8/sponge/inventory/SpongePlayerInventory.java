/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.sponge.inventory;

import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.inventory.ItemStack;
import dev.neuralnexus.taterapi.inventory.PlayerInventory;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Sponge implementation of {@link PlayerInventory}. */
public class SpongePlayerInventory extends SpongeInventory implements PlayerInventory {
    private final CarriedInventory<Player> playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Sponge player inventory.
     */
    public SpongePlayerInventory(CarriedInventory<Player> playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    @Override
    public List<ItemStack> armor() {
        List<ItemStack> armorContents = new ArrayList<>(4);
        playerInventory.forEach(
                inventory -> {
                    if (inventory instanceof EquipmentInventory) {
                        EquipmentInventory armor = (EquipmentInventory) inventory;
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
                    }
                });
        return armorContents;
    }

    @Override
    public void setArmor(List<ItemStack> armor) {
        playerInventory.forEach(
                inventory -> {
                    if (inventory instanceof EquipmentInventory) {
                        EquipmentInventory armorInventory = (EquipmentInventory) inventory;
                        armorInventory.clear();
                        armor.stream()
                                .map(SpongeItemStack.class::cast)
                                .map(SpongeItemStack::itemStack)
                                .forEach(armorInventory::offer);
                    }
                });
    }

    @Override
    public ItemStack offhand() {
        if (MinecraftVersion.get().isOlderThan(MinecraftVersion.V1_9)) {
            throw new VersionFeatureNotSupportedException();
        }
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setOffhand(ItemStack offhand) {
        if (MinecraftVersion.get().isOlderThan(MinecraftVersion.V1_9)) {
            throw new VersionFeatureNotSupportedException();
        }
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public int selectedSlot() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
