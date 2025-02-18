/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_15.vanilla.block;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.BlockPos;

import net.minecraft.core.Registry;

/** Vanilla implementation of {@link Block}. */
public class VanillaBlock implements Block, Wrapped<net.minecraft.world.level.block.Block> {
    private final net.minecraft.core.BlockPos pos;
    private final net.minecraft.world.level.block.Block block;

    public VanillaBlock(
            net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    @Override
    public net.minecraft.world.level.block.Block unwrap() {
        return this.block;
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) Registry.BLOCK.getKey(this.block);
    }

    @Override
    public BlockPos blockPos() {
        return new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ());
    }
}
