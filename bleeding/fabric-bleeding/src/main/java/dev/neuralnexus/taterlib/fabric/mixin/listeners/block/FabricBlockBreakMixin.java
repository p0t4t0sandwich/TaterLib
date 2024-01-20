package dev.neuralnexus.taterlib.fabric.mixin.listeners.block;

import dev.neuralnexus.taterlib.fabric.event.api.FabricBlockEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the block break listener. */
@Mixin(Block.class)
public class FabricBlockBreakMixin {
    /**
     * Called when a block is broken.
     *
     * @param level The world.
     * @param itemStack The item stack
     * @param blockPos The position of the block.
     * @param blockState The state of the block.
     * @param blockEntity The block entity
     * @param player The player that broke the block.
     * @param ci The callback info.
     */
    @Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
    private void onBreak(
            Level level,
            Player player,
            BlockPos blockPos,
            BlockState blockState,
            BlockEntity blockEntity,
            ItemStack itemStack,
            CallbackInfo ci) {
        FabricBlockEvents.BLOCK_BREAK
                .invoker()
                .onBlockBreak(level, player, blockPos, blockState, blockEntity, itemStack, ci);
    }
}
