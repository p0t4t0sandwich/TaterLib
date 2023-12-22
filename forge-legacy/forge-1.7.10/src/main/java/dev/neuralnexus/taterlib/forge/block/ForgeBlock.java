package dev.neuralnexus.taterlib.forge.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.utils.Position;

/** Forge implementation of {@link Block}. */
public class ForgeBlock implements Block {
    private final Position pos;
    private final net.minecraft.block.Block block;

    public ForgeBlock(Position pos, net.minecraft.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return block.getLocalizedName().split("block\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public Position getBlockPos() {
        return new Position(pos.getX(), pos.getY(), pos.getZ());
    }
}
