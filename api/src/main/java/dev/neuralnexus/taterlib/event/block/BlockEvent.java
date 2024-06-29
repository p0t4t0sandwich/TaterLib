/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

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
