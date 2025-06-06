/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.block;

import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.BlockPos;

/** Abstract implementation of a block. */
public interface Block {
    /**
     * Get the type of the block.
     *
     * @return The type of the block.
     */
    ResourceKey type();

    /**
     * Get the block location.
     *
     * @return The block location.
     */
    BlockPos blockPos();
}
