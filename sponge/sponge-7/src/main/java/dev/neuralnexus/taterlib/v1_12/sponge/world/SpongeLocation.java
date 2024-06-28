/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_12.sponge.world;

import dev.neuralnexus.taterlib.world.BlockPos;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

/** Sponge implementation of {@link Location}. */
public class SpongeLocation implements Location {
    private final org.spongepowered.api.world.Location<org.spongepowered.api.world.World> location;

    /** Creates a new location. */
    public SpongeLocation(
            org.spongepowered.api.world.Location<org.spongepowered.api.world.World> location) {
        this.location = location;
    }

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        // TODO: Check to see of Location.position() is mutable
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        // TODO: Check to see of Location.position() is mutable
    }

    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        // TODO: Check to see of Location.position() is mutable
    }

    /** {@inheritDoc} */
    @Override
    public float yaw() {
        // TODO: Find a way to get the yaw
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public void setYaw(float yaw) {
        // TODO: Find a way to set the yaw
    }

    /** {@inheritDoc} */
    @Override
    public float pitch() {
        // TODO: Find a way to get the pitch
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public void setPitch(float pitch) {
        // TODO: Find a way to set the pitch
    }

    /** {@inheritDoc} */
    @Override
    public BlockPos blockPosition() {
        return new BlockPos(location.getX(), location.getY(), location.getZ());
    }

    /** {@inheritDoc} */
    @Override
    public World world() {
        return new SpongeWorld(location.getExtent());
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(World world) {
        location.setExtent(((SpongeWorld) world).world());
    }
}
