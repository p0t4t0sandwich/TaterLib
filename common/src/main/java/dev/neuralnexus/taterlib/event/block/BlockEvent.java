package dev.neuralnexus.taterlib.event.block;

import dev.neuralnexus.taterlib.block.Block;

/** Represents a block event. */
public interface BlockEvent {
    /**
     * Gets the block that was broken.
     *
     * @return The block that was broken.
     */
    Block getBlock();
}
