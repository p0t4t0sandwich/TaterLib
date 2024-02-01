package dev.neuralnexus.taterlib.vanilla.mixin.listeners.world.level.block.block;

import dev.neuralnexus.taterlib.event.api.BlockEvents;
import dev.neuralnexus.taterlib.vanilla.event.VanillaCancellableCallbackWrapper;
import dev.neuralnexus.taterlib.vanilla.event.block.VanillaPlayerBlockBreakEvent;

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
public class BlockMixin_1_20 {
    /** Called when a block is broken. */
    @Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
    private void onBreak(
            Level level,
            Player player,
            BlockPos blockPos,
            BlockState blockState,
            BlockEntity blockEntity,
            ItemStack itemStack,
            CallbackInfo ci) {
        BlockEvents.BLOCK_BREAK.invoke(
                new VanillaPlayerBlockBreakEvent(
                        level,
                        player,
                        blockPos,
                        blockState,
                        new VanillaCancellableCallbackWrapper(ci)));
    }
}
