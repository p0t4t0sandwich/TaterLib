package dev.neuralnexus.taterlib.v1_21.vanilla.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.world.BlockPos;

/** Vanilla implementation of {@link Block}. */
public class VanillaBlock implements Block {
    private final net.minecraft.core.BlockPos pos;
    private final net.minecraft.world.level.block.Block block;

    public VanillaBlock(
            net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return block.getDescriptionId().split("block\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public BlockPos blockPos() {
        return new BlockPos(pos.getX(), pos.getY(), pos.getZ());
    }
}
