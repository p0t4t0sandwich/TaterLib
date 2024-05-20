package dev.neuralnexus.taterlib.v1_15_1.forge.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.world.BlockPos;

/** Forge implementation of {@link Block}. */
public class ForgeBlock implements Block {
    private final net.minecraft.util.math.BlockPos pos;
    private final net.minecraft.block.Block block;

    public ForgeBlock(net.minecraft.util.math.BlockPos pos, net.minecraft.block.Block block) {
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
