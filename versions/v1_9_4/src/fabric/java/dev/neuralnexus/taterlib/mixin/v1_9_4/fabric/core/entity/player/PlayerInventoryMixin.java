/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_9_4.fabric.core.entity.player;

import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.player.PlayerInventoryBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.item.inventory.WrappedItemStack;

import net.minecraft.entity.player.PlayerInventory;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V9, max = MinecraftVersion.V10_2)
@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin implements PlayerInventoryBridge {
    @Final @Shadow public net.minecraft.item.ItemStack[] armorSlots;

    @Shadow @Final public net.minecraft.item.ItemStack[] offhand;

    @Override
    public List<ItemStack> bridge$armor() {
        return Arrays.stream(this.armorSlots)
                .map(WrappedItemStack::new)
                .collect(Collectors.toList());
    }

    @Override
    public void bridge$setArmor(List<ItemStack> armor) {
        for (int i = 0; i < this.armorSlots.length; i++) {
            this.armorSlots[i] = ((WrappedItemStack) armor.get(i)).unwrap();
        }
    }

    @Override
    public ItemStack bridge$offhand() {
        return new WrappedItemStack(this.offhand[0]);
    }

    @Override
    public void bridge$setOffhand(ItemStack offhand) {
        this.offhand[0] = ((WrappedItemStack) offhand).unwrap();
    }
}
