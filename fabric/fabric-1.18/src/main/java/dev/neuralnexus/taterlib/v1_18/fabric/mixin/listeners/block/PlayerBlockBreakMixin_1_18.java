package dev.neuralnexus.taterlib.v1_18.fabric.mixin.listeners.block;

import dev.neuralnexus.taterlib.v1_18.fabric.event.api.FabricBlockEvents;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the block break listener. */
@Mixin(Block.class)
public class PlayerBlockBreakMixin_1_18 {
    /**
     * Called when a block is broken.
     *
     * @param world The world.
     * @param pos The position of the block.
     * @param state The state of the block.
     * @param player The player that broke the block.
     * @param ci The callback info.
     */
    @Inject(method = "onBreak", at = @At("HEAD"), cancellable = true)
    private void onBreak(
            World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        FabricBlockEvents.BLOCK_BREAK.invoker().onBlockBreak(world, pos, state, player, ci);
    }
}
