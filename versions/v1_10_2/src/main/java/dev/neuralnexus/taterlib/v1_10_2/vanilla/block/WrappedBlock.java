/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_10_2.vanilla.block;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.BlockPos;

/** Forge implementation of {@link Block}. */
public class WrappedBlock implements Block, Wrapped<net.minecraft.block.Block> {
    private final int x;
    private final int y;
    private final int z;
    private final net.minecraft.block.Block block;

    public WrappedBlock(int x, int y, int z, net.minecraft.block.Block block) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.block = block;
    }

    @Override
    public net.minecraft.block.Block unwrap() {
        return this.block;
    }

    @Override
    public ResourceKey type() {
        // TODO: Find block registry
        return ResourceKey.of(
                this.block.getTranslationKey().split("block\\.")[1].replace(".", ":"));
    }

    @Override
    public BlockPos blockPos() {
        return new BlockPos(this.x, this.y, this.z);
    }
}
