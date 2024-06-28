/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_21.vanilla.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.world.BlockPos;

/** Vanilla implementation of {@link Block}. */
public class VanillaBlock implements Block {
    private final net.minecraft.core.BlockPos pos;
    private final net.minecraft.world.level.block.Block block;

    public VanillaBlock(
            net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return block.getDescriptionId().split("block\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public BlockPos blockPos() {
        return new BlockPos(pos.getX(), pos.getY(), pos.getZ());
    }
}
