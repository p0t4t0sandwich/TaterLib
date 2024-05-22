package dev.neuralnexus.taterlib.v1_14.fabric.event.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
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
                            (world, pos, state, player, ci) -> {
                                for (BlockBreak listener : listeners) {
                                    listener.onBlockBreak(world, pos, state, player, ci);
                                }
                            });

    @FunctionalInterface
    public interface BlockBreak {
        void onBlockBreak(
                World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci);
    }
}
