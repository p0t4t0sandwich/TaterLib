/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13.sponge.block;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.BlockPos;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.server.ServerLocation;

/** Forge implementation of {@link Block}. */
public class SpongeBlock implements Block, Wrapped<BlockState> {
    private final ServerLocation pos;
    private final BlockState block;

    public SpongeBlock(ServerLocation location) {
        this.pos = location;
        this.block = location.block();
    }

    @Override
    public BlockState unwrap() {
        return this.block;
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) BlockTypes.registry().valueKey(this.block.type());
    }

    @Override
    public BlockPos blockPos() {
        return new BlockPos(this.pos.blockX(), this.pos.blockY(), this.pos.blockZ());
    }
}
