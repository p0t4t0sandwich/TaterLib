package dev.neuralnexus.taterlib.fabric.event.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.block.BlockEvent;
import dev.neuralnexus.taterlib.vanilla.block.VanillaBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Called when a block event occurs. */
public class FabricBlockEvent implements BlockEvent {
    private final Level level;
    private final Player player;
    private final BlockPos blockPos;
    private final BlockState blockState;
    private final CallbackInfo ci;

    public FabricBlockEvent(
            Level level, Player player, BlockPos blockPos, BlockState blockState, CallbackInfo ci) {
        this.level = level;
        this.player = player;
        this.blockPos = blockPos;
        this.blockState = blockState;
        this.ci = ci;
    }

    /** {@inheritDoc} */
    @Override
    public Block getBlock() {
        return new VanillaBlock(this.blockPos, this.blockState.getBlock());
    }
}
