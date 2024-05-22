package dev.neuralnexus.taterlib.v1_8_9.fabric.event.api;

import net.legacyfabric.fabric.api.event.Event;
import net.legacyfabric.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Contains additional block events. */
public class FabricBlockEvents {
    /** Called when a block is broken. */
    public static final Event<BlockBreak> BLOCK_BREAK =
            EventFactory.createArrayBacked(
                    BlockBreak.class,
                    (listeners) ->
                            (world, player, pos, state, blockEntity, ci) -> {
                                for (BlockBreak listener : listeners) {
                                    listener.onBlockBreak(
                                            world, player, pos, state, blockEntity, ci);
                                }
                            });

    @FunctionalInterface
    public interface BlockBreak {
        void onBlockBreak(
                World world,
                PlayerEntity player,
                BlockPos pos,
                BlockState state,
                BlockEntity blockEntity,
                CallbackInfo ci);
    }
}
