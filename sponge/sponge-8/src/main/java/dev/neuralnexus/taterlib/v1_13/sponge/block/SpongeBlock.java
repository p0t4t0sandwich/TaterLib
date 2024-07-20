/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13.sponge.block;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.BlockPos;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.server.ServerLocation;

/** Forge implementation of {@link Block}. */
public class SpongeBlock implements Block {
    private final ServerLocation pos;
    private final BlockState block;

    public SpongeBlock(ServerLocation location) {
        this.pos = location;
        this.block = location.block();
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) (Object) BlockTypes.registry().valueKey(block.type());
    }

    @Override
    public BlockPos blockPos() {
        return new BlockPos(pos.blockX(), pos.blockY(), pos.blockZ());
    }
}
