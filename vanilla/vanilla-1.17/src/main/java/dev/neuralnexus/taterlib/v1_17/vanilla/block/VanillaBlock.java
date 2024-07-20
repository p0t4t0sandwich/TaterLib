/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_17.vanilla.block;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.BlockPos;

import net.minecraft.core.Registry;

/** Vanilla implementation of {@link Block}. */
public class VanillaBlock implements Block {
    private final net.minecraft.core.BlockPos pos;
    private final net.minecraft.world.level.block.Block block;

    public VanillaBlock(
            net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.Block block) {
        this.pos = pos;
        this.block = block;
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) (Object) Registry.BLOCK.getKey(block);
    }

    @Override
    public BlockPos blockPos() {
        return new BlockPos(pos.getX(), pos.getY(), pos.getZ());
    }
}
