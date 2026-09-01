/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
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
