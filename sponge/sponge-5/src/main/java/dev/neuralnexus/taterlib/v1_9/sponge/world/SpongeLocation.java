/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_9.sponge.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;

/** Sponge implementation of {@link Location}. */
public class SpongeLocation implements Location {
    private final org.spongepowered.api.world.Location<org.spongepowered.api.world.World> location;

    /** Creates a new location. */
    public SpongeLocation(
            org.spongepowered.api.world.Location<org.spongepowered.api.world.World> location) {
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
        return new BlockPos(location.getX(), location.getY(), location.getZ());
    }

    @Override
    public World world() {
        return new SpongeWorld(location.getExtent());
    }

    @Override
    public void setWorld(World world) {
        location.setExtent(((SpongeWorld) world).world());
    }
}
