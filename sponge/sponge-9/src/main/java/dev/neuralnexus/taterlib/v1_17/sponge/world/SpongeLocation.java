/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_17.sponge.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;

/** Sponge implementation of {@link Location}. */
@SuppressWarnings("FieldCanBeLocal")
public class SpongeLocation implements Location {
    private final org.spongepowered.api.world.server.ServerLocation location;
    private org.spongepowered.api.world.server.ServerWorld world;

    /** Creates a new location. */
    public SpongeLocation(org.spongepowered.api.world.server.ServerLocation location) {
        this.location = location;
    }

    @Override
    public void setX(double x) {
        // TODO: Check to see of Location.position() is mutable
    }

    @Override
    public void setY(double y) {
        // TODO: Check to see of Location.position() is mutable
    }

    @Override
    public void setZ(double z) {
        // TODO: Check to see of Location.position() is mutable
    }

    @Override
    public float yaw() {
        // TODO: Find a way to get the yaw
        return 0;
    }

    @Override
    public void setYaw(float yaw) {
        // TODO: Find a way to set the yaw
    }

    @Override
    public float pitch() {
        // TODO: Find a way to get the pitch
        return 0;
    }

    @Override
    public void setPitch(float pitch) {
        // TODO: Find a way to set the pitch
    }

    @Override
    public BlockPos blockPosition() {
        return new BlockPos(location.blockX(), location.blockY(), location.blockZ());
    }

    @Override
    public World world() {
        // TODO: Find a way to get the world
        return new SpongeWorld(location.world());
    }

    @Override
    public void setWorld(World world) {
        this.world = ((SpongeServerWorld) world).world();
    }
}
