/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.block;

import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.BlockPos;

/** Abstract implementation of a block. */
public interface Block {
    /**
     * Get the type of the block.
     *
     * @return The type of the block.
     */
    Identifier type();

    /**
     * Get the block location.
     *
     * @return The block location.
     */
    BlockPos blockPos();
}
