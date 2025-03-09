/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.event.block;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.event.block.BlockEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.block.WrappedBlock;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Vanilla implementation of {@link BlockEvent}. */
public class VanillaBlockEvent implements BlockEvent {
    private final BlockPos pos;
    private final net.minecraft.block.Block block;

    public VanillaBlockEvent(BlockPos pos, World world, net.minecraft.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    @Override
    public Block block() {
        return new WrappedBlock(this.pos, this.block);
    }
}
