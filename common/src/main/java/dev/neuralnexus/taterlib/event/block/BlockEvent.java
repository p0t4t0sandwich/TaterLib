package dev.neuralnexus.taterlib.event.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.Event;

/** Represents a block event. */
public interface BlockEvent extends Event {
    /**
     * Gets the block that was broken.
     *
     * @return The block that was broken.
     */
    Block block();
}
