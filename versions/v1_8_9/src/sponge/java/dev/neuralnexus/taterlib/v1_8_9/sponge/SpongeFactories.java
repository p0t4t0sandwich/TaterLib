/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

public class SpongeFactories {
    // Block
    public static BlockFromEvent blockFromEvent = event -> null;
    // ItemStack
    public static ItemStackType itemStackType = itemStack -> null;
    public static NilItemStack nilItemStack = () -> null;
    // PlayerInventory
    public static EquipmentFromInventory equipmentFromInventory = event -> null;
    public static InventoryOffHand inventoryOffHand = event -> null;
    public static InventorySetOffHand inventorySetOffHand = (event, itemStack) -> {};
    public static InventorySelectedSlot inventorySelectedSlot = event -> -1;
    // Entity
    public static EntityBiome entityBiome = event -> null;
    // Living
    public static AbsorptionAmount absorptionAmount = event -> 0;
    public static SetAbsorptionAmount setAbsorptionAmount = (event, amount) -> {};
    //  DestructEntityEvent.Death
    public static KeepInventory keepInventory = event -> false;
    public static SetKeepInventory setKeepInventory = (event, keepInventory) -> {};

    @FunctionalInterface
    public interface BlockFromEvent {
        Block get(ChangeBlockEvent.Pre event);
    }

    @FunctionalInterface
    public interface ItemStackType {
        ResourceKey get(ItemStack itemStack);
    }

    @FunctionalInterface
    public interface NilItemStack {
        ItemStack get();
    }

    @FunctionalInterface
    public interface EquipmentFromInventory {
        EquipmentInventory get(CarriedInventory<?> inventory);
    }

    @FunctionalInterface
    public interface InventoryOffHand {
        dev.neuralnexus.taterapi.item.inventory.ItemStack get(CarriedInventory<?> inventory);
    }

    @FunctionalInterface
    public interface InventorySetOffHand {
        void set(
                CarriedInventory<?> inventory,
                dev.neuralnexus.taterapi.item.inventory.ItemStack itemStack);
    }

    @FunctionalInterface
    public interface InventorySelectedSlot {
        int get(CarriedInventory<?> inventory);
    }

    @FunctionalInterface
    public interface EntityBiome {
        ResourceKey get(Entity entity);
    }

    @FunctionalInterface
    public interface AbsorptionAmount {
        double get(Living entity);
    }

    @FunctionalInterface
    public interface SetAbsorptionAmount {
        void set(Living entity, double amount);
    }

    @FunctionalInterface
    public interface KeepInventory {
        boolean get(DestructEntityEvent.Death event);
    }

    @FunctionalInterface
    public interface SetKeepInventory {
        void set(DestructEntityEvent.Death event, boolean keepInventory);
    }
}
