package dev.neuralnexus.taterlib.forge.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.world.BlockPos;

import net.minecraft.util.ChunkCoordinates;

/** Forge implementation of {@link Block}. */
public class ForgeBlock implements Block {
    private final ChunkCoordinates pos;
    private final net.minecraft.block.Block block;

    public ForgeBlock(ChunkCoordinates pos, net.minecraft.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return block.getLocalizedName().split("block\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public BlockPos blockPos() {
        return new BlockPos(pos.posX, pos.posY, pos.posZ);
    }
}
