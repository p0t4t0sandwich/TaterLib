package dev.neuralnexus.taterlib.v1_9_4.fabric.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.world.BlockPos;

/** Fabric implementation of {@link Block}. */
public class FabricBlock implements Block {
    private final net.minecraft.util.math.BlockPos pos;
    private final net.minecraft.block.Block block;

    public FabricBlock(net.minecraft.util.math.BlockPos pos, net.minecraft.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return block.getTranslationKey().split("block\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public BlockPos blockPos() {
        return new BlockPos(pos.getX(), pos.getY(), pos.getZ());
    }
}
