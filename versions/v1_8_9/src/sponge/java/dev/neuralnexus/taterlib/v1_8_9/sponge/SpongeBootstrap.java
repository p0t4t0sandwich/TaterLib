/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_8_9.sponge.block.SpongeBlock;

import org.spongepowered.api.item.inventory.entity.HumanInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SpongeBootstrap {
    public static void init() {
        SpongeFactories.blockFromEvent =
                event -> {
                    List<Location<World>> locations = event.getLocations();
                    if (!locations.isEmpty()) {
                        Location<World> location = locations.get(0);
                        return new SpongeBlock(location);
                    }
                    return null;
                };

        SpongeFactories.itemStackType =
                itemStack -> ResourceKey.of(itemStack.getItem().getType().getName());
        SpongeFactories.nilItemStack = () -> null;

        SpongeFactories.equipmentFromInventory =
                inventory -> {
                    AtomicReference<EquipmentInventory> armor = new AtomicReference<>();
                    inventory.forEach(
                            inv -> {
                                if (inv instanceof EquipmentInventory) {
                                    armor.set((EquipmentInventory) inv);
                                }
                            });
                    if (armor.get() == null) {
                        throw new IllegalStateException(
                                "Player inventory does not contain armor inventory");
                    }
                    return armor.get();
                };

        SpongeFactories.inventoryOffHand =
                inventory -> {
                    throw new VersionFeatureNotSupportedException();
                };
        SpongeFactories.inventorySetOffHand =
                (inventory, itemStack) -> {
                    throw new VersionFeatureNotSupportedException();
                };
        SpongeFactories.inventorySelectedSlot =
                inventory -> {
                    if (inventory instanceof HumanInventory) {
                        return ((HumanInventory) inventory).getHotbar().getSelectedSlotIndex();
                    }
                    return -1;
                };

        SpongeFactories.entityBiome =
                entity ->
                        ResourceKey.of(
                                entity.getWorld()
                                        .getBiome(
                                                entity.getLocation().getBlockPosition().toVector2())
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
