/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_9_4.fabric.block;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.BlockPos;

/** Fabric implementation of {@link Block}. */
public class FabricBlock implements Block, Wrapped<net.minecraft.block.Block> {
    private final net.minecraft.util.math.BlockPos pos;
    private final net.minecraft.block.Block block;

    public FabricBlock(net.minecraft.util.math.BlockPos pos, net.minecraft.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    @Override
    public net.minecraft.block.Block unwrap() {
        return this.block;
    }

    @Override
    public ResourceKey type() {
        return ResourceKey.of(
                this.block.getTranslationKey().split("block\\.")[1].replace(".", ":"));
    }

    @Override
    public BlockPos blockPos() {
        return new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ());
    }
}
