package dev.neuralnexus.taterlib.v1_20.vanilla.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.utils.Position;

import net.minecraft.core.BlockPos;

/** Vanilla implementation of {@link Block}. */
public class VanillaBlock implements Block {
    private final BlockPos pos;
    private final net.minecraft.world.level.block.Block block;

    public VanillaBlock(BlockPos pos, net.minecraft.world.level.block.Block block) {
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
    public Position blockPos() {
        return new Position(pos.getX(), pos.getY(), pos.getZ());
    }
}
