package dev.neuralnexus.taterlib.forge.block;

import dev.neuralnexus.taterlib.common.block.Block;
import dev.neuralnexus.taterlib.common.utils.Position;
import net.minecraft.core.BlockPos;

/**
 * Forge implementation of {@link Block}.
 */
public class ForgeBlock implements Block {
    private final BlockPos pos;
    private final net.minecraft.world.level.block.Block block;

    public ForgeBlock(BlockPos pos, net.minecraft.world.level.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return block.getDescriptionId().split("block\\.")[1].replace(".", ":");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getBlockPos() {
        return new Position(pos.getX(), pos.getY(), pos.getZ());
    }
}
