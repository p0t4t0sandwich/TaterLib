package dev.neuralnexus.taterlib.common.block;

import dev.neuralnexus.taterlib.common.utils.Position;

/** Abstract implementation of a block. */
public interface Block {
    /**
     * Get the type of the block.
     *
     * @return The type of the block.
     */
    String getType();

    /**
     * Get the block location.
     *
     * @return The block location.
     */
    Position getBlockPos();
}
