package dev.neuralnexus.taterlib.v1_7_10.fabric.mixin.listeners.block;

import dev.neuralnexus.taterlib.v1_7_10.fabric.event.api.FabricBlockEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the block break listener. */
@Mixin(Block.class)
public class PlayerBlockBreakMixin_1_7_10 {
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
    private static void onBlockBreak(
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
