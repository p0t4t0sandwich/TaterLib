package dev.neuralnexus.taterlib.sponge.util;

import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.utils.Position;

/** Sponge implementation of {@link Location}. */
public class SpongeLocation implements Location {
    private final org.spongepowered.api.world.Location location;

    /** Creates a new location. */
    public SpongeLocation(org.spongepowered.api.world.Location location) {
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
    public Position blockPosition() {
        return new Position(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /** {@inheritDoc} */
    @Override
    public String world() {
        // TODO: Find a way to get the world
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(String world) {
        // TODO: Find a way to set the world
    }
}
