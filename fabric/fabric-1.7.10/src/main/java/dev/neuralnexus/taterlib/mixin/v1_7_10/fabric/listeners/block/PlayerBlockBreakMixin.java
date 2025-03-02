/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.fabric.listeners.block;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.api.FabricBlockEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V7_2, max = MinecraftVersion.V7_10)
@Mixin(Block.class)
public class PlayerBlockBreakMixin {
    /**
     * Called when a block is broken.
     *
     * @param world The world.
     * @param i The x position of the block.
     * @param j The y position of the block.
     * @param k The z position of the block.
     * @param l The metadata of the block.
     * @param ci The callback info.
     */
    @Inject(method = "method_424", at = @At("HEAD"), cancellable = true)
    private void onBlockBreak(
            World world, PlayerEntity playerEntity, int i, int j, int k, int l, CallbackInfo ci) {
        FabricBlockEvents.BLOCK_BREAK
                .invoker()
                .onBlockBreak(
                        world,
                        playerEntity,
                        new BlockPos(i, j, k),
                        l,
                        world.getBlockEntity(i, j, k),
                        ci);
    }
}
