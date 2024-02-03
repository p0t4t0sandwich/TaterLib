package dev.neuralnexus.taterlib.v1_19.vanilla.event.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.block.BlockEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.block.VanillaBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/** Vanilla implementation of {@link BlockEvent}. */
public class VanillaBlockEvent implements BlockEvent {
    private final Level level;
    private final BlockPos blockPos;
    private final BlockState blockState;

    public VanillaBlockEvent(Level level, BlockPos blockPos, BlockState blockState) {
        this.level = level;
        this.blockPos = blockPos;
        this.blockState = blockState;
    }

    /** {@inheritDoc} */
    @Override
    public Block getBlock() {
        return new VanillaBlock(blockPos, blockState.getBlock());
    }
}
