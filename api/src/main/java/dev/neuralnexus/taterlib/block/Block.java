package dev.neuralnexus.taterlib.block;

import dev.neuralnexus.taterlib.world.BlockPos;

/** Abstract implementation of a block. */
public interface Block {
    /**
     * Get the type of the block.
     *
     * @return The type of the block.
     */
    String type();

    /**
     * Get the block location.
     *
     * @return The block location.
     */
    BlockPos blockPos();
}
