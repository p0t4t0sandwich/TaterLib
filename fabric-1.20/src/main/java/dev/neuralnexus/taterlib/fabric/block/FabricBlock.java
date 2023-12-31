package dev.neuralnexus.taterlib.fabric.block;

import dev.neuralnexus.taterlib.common.block.Block;
import dev.neuralnexus.taterlib.common.utils.Position;
import net.minecraft.util.math.BlockPos;

/**
 * Fabric implementation of {@link Block}.
 */
public class FabricBlock implements Block {
    private final BlockPos pos;
    private final net.minecraft.block.Block block;

    public FabricBlock(BlockPos pos, net.minecraft.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return block.getTranslationKey().split("block\\.")[1].replace(".", ":");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getBlockPos() {
        return new Position(pos.getX(), pos.getY(), pos.getZ());
    }
}
