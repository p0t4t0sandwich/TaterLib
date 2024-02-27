package dev.neuralnexus.taterlib.sponge.world;

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
    public double x() {
        return location.getX();
    }

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        // TODO: Check to see of Location.position() is mutable
    }

    /** {@inheritDoc} */
    @Override
    public double blockX() {
        return location.getBlockX();
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return location.getY();
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        // TODO: Check to see of Location.position() is mutable
    }

    /** {@inheritDoc} */
    @Override
    public double blockY() {
        return location.getBlockY();
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return location.getZ();
    }

    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        // TODO: Check to see of Location.position() is mutable
    }

    /** {@inheritDoc} */
    @Override
    public double blockZ() {
        return location.getBlockZ();
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
        return new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
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
