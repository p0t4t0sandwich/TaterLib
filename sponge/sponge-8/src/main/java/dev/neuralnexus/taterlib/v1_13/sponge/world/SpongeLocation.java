package dev.neuralnexus.taterlib.v1_13.sponge.world;

import dev.neuralnexus.taterlib.world.BlockPos;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

/** Sponge implementation of {@link Location}. */
public class SpongeLocation implements Location {
    private final org.spongepowered.api.world.server.ServerLocation location;
    private org.spongepowered.api.world.server.ServerWorld world;

    /** Creates a new location. */
    public SpongeLocation(org.spongepowered.api.world.server.ServerLocation location) {
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
        return new BlockPos(location.blockX(), location.blockY(), location.blockZ());
    }

    /** {@inheritDoc} */
    @Override
    public World world() {
        // TODO: Find a way to get the world
        return new SpongeWorld(location.world());
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(World world) {
        this.world = ((SpongeServerWorld) world).world();
    }
}
