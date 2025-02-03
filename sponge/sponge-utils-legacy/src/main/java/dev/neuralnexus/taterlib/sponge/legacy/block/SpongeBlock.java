/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.sponge.legacy.block;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.BlockPos;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/** Sponge implementation of {@link Block}. */
public class SpongeBlock implements Block, Wrapped<BlockState> {
    private final Location<World> pos;
    private final BlockState block;

    public SpongeBlock(Location<World> location) {
        this.pos = location;
        this.block = location.getBlock();
    }

    @Override
    public BlockState unwrap() {
        return this.block;
    }

    @Override
    public ResourceKey type() {
        return ResourceKey.of(
                this.block.getType().toString().split("entity\\.")[1].replace(".", ":"));
    }

    @Override
    public BlockPos blockPos() {
        return new BlockPos(this.pos.getBlockX(), this.pos.getBlockY(), this.pos.getBlockZ());
    }
}
