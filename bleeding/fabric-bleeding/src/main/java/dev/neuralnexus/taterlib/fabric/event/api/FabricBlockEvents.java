package dev.neuralnexus.taterlib.fabric.event.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Contains additional block events. */
public class FabricBlockEvents {
    /** Called when a block is broken. */
    public static final Event<BlockBreak> BLOCK_BREAK =
            EventFactory.createArrayBacked(
                    BlockBreak.class,
                    (listeners) ->
                            (level, player, blockPos, blockState, blockEntity, itemStack, ci) -> {
                                for (BlockBreak listener : listeners) {
                                    listener.onBlockBreak(
                                            level,
                                            player,
                                            blockPos,
                                            blockState,
                                            blockEntity,
                                            itemStack,
                                            ci);
                                }
                            });

    @FunctionalInterface
    public interface BlockBreak {
        void onBlockBreak(
                Level level,
                Player player,
                BlockPos blockPos,
                BlockState blockState,
                BlockEntity blockEntity,
                ItemStack itemStack,
                CallbackInfo ci);
    }
}
