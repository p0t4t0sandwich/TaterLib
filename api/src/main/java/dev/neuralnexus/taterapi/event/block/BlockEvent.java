/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.block;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.event.Event;

/** Represents a block event. */
public interface BlockEvent extends Event {
    /**
     * Gets the block that was broken.
     *
     * @return The block that was broken.
     */
    Block block();
}
