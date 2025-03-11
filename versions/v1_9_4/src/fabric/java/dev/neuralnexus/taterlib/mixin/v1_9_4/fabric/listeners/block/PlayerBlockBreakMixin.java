/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_9_4.fabric.listeners.block;

import dev.neuralnexus.taterapi.event.api.BlockEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.mixin.MixinCancellableCallbackWrapper;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.block.VanillaPlayerBlockBreakEvent;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.state.BlockState;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V9, max = MinecraftVersion.V11_2)
@Mixin(Block.class)
public class PlayerBlockBreakMixin {
    @Inject(method = "afterMinedByPlayer", at = @At("HEAD"), cancellable = true)
    private void onBlockBreak(
            World world,
            PlayerEntity player,
            BlockPos pos,
            BlockState state,
            BlockEntity blockEntity,
            ItemStack stack,
            CallbackInfo ci) {
        BlockEvents.PLAYER_BLOCK_BREAK.invoke(
                new VanillaPlayerBlockBreakEvent(
                        pos,
                        world,
                        (Block) (Object) this,
                        player,
                        new MixinCancellableCallbackWrapper(ci)));
    }
}
