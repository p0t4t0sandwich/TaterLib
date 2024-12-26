/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_8_9.fabric.listeners.block;

import dev.neuralnexus.modapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterlib.v1_8_9.fabric.event.api.FabricBlockEvents;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the block break listener. */
@ReqMappings(Mappings.LEGACYINTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V1_8, max = MinecraftVersion.V1_8_9)
@Mixin(Block.class)
public class PlayerBlockBreakMixin {
    /**
     * Called when a block is broken.
     *
     * @param world The world.
     * @param pos The position of the block.
     * @param ci The callback info.
     */
    @Inject(method = "harvest", at = @At("HEAD"), cancellable = true)
    private void onBlockBreak(
            World world,
            PlayerEntity player,
            BlockPos pos,
            BlockState state,
            BlockEntity be,
            CallbackInfo ci) {
        FabricBlockEvents.BLOCK_BREAK.invoker().onBlockBreak(world, player, pos, state, be, ci);
    }
}
