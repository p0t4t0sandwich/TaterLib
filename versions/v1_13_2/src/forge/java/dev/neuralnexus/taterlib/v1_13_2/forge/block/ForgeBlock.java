/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.block;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterlib.v1_13_2.forge.resource.ForgeResourceKey;

import net.minecraft.util.registry.IRegistry;

/** Forge implementation of {@link Block}. */
public class ForgeBlock implements Block, Wrapped<net.minecraft.block.Block> {
    private final net.minecraft.util.math.BlockPos pos;
    private final net.minecraft.block.Block block;

    public ForgeBlock(net.minecraft.util.math.BlockPos pos, net.minecraft.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    @Override
    public net.minecraft.block.Block unwrap() {
        return this.block;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ResourceKey type() {
        return new ForgeResourceKey(IRegistry.field_212618_g.getKey(this.block));
    }

    @Override
    public BlockPos blockPos() {
        return new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ());
    }
}
