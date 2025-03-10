/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_10_2.sponge;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_8_9.sponge.SpongeFactories;
import dev.neuralnexus.taterlib.v1_8_9.sponge.block.SpongeBlock;
import dev.neuralnexus.taterlib.v1_8_9.sponge.item.inventory.SpongeItemStack;

import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.item.inventory.entity.PlayerInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.world.LocatableBlock;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class SpongeBootstrap {
    public static void init() {
        SpongeFactories.blockFromEvent =
                event -> {
                    Cause cause = event.getCause();
                    LocatableBlock locatableBlock = cause.first(LocatableBlock.class).orElse(null);
                    if (locatableBlock == null) {
                        return null;
                    }
                    Location<World> location = locatableBlock.getLocation();
                    return new SpongeBlock(location);
                };

        SpongeFactories.itemStackType =
                itemStack -> ResourceKey.of(itemStack.getItem().getType().getName());
        SpongeFactories.nilItemStack = () -> null;

        SpongeFactories.equipmentFromInventory =
                inventory -> {
                    EquipmentInventory armor = null;
                    if (inventory instanceof PlayerInventory) {
                        armor = ((PlayerInventory) inventory).getEquipment();
                    }
                    if (armor == null) {
                        throw new IllegalStateException(
                                "Player inventory does not contain armor inventory");
                    }
                    return armor;
                };

        SpongeFactories.inventoryOffHand =
                inventory -> {
                    if (inventory instanceof PlayerInventory) {
                        return new SpongeItemStack(
                                ((PlayerInventory) inventory).getOffhand().first());
                    } else {
                        throw new IllegalStateException("Inventory is not a player inventory");
                    }
                };
        SpongeFactories.inventorySetOffHand =
                (inventory, itemStack) -> {
                    if (inventory instanceof PlayerInventory) {
                        ((PlayerInventory) inventory)
                                .getOffhand()
                                .set(((SpongeItemStack) itemStack).unwrap());
                    } else {
                        throw new IllegalStateException("Inventory is not a player inventory");
                    }
                };
        SpongeFactories.inventorySelectedSlot =
                inventory -> {
                    if (inventory instanceof PlayerInventory) {
                        return ((PlayerInventory) inventory).getHotbar().getSelectedSlotIndex();
                    }
                    return -1;
                };

        SpongeFactories.entityBiome =
                entity ->
                        ResourceKey.of(
                                entity.getWorld()
                                        .getBiome(entity.getLocation().getBlockPosition())
                                        .getId());

        SpongeFactories.absorptionAmount =
                entity -> {
                    throw new VersionFeatureNotSupportedException();
                };
        SpongeFactories.setAbsorptionAmount =
                (entity, amount) -> {
                    throw new VersionFeatureNotSupportedException();
                };
    }
}
