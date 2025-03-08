/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.event.block;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.event.block.BlockEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.block.WrappedBlock;

import net.minecraft.world.World;

/** Vanilla implementation of {@link BlockEvent}. */
public class VanillaBlockEvent implements BlockEvent {
    private final int x;
    private final int y;
    private final int z;
    private final net.minecraft.block.Block block;

    public VanillaBlockEvent(
            int x, int y, int z, World world, net.minecraft.block.Block block, int blockMetadata) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.block = block;
    }

    @Override
    public Block block() {
        return new WrappedBlock(this.x, this.y, this.z, this.block);
    }
}
