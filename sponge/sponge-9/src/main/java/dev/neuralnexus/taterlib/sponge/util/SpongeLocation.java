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
        return location.x();
    }

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        // TODO: Check to see of Location.position() is mutable
    }

    /** {@inheritDoc} */
    @Override
    public double blockX() {
        return location.blockX();
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return location.y();
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        // TODO: Check to see of Location.position() is mutable
    }

    /** {@inheritDoc} */
    @Override
    public double blockY() {
        return location.blockY();
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return location.z();
    }

    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        // TODO: Check to see of Location.position() is mutable
    }

    /** {@inheritDoc} */
    @Override
    public double blockZ() {
        return location.blockZ();
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
        return new Position(location.blockX(), location.blockY(), location.blockZ());
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
