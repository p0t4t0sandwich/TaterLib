package dev.neuralnexus.taterlib.sponge.util;

import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.common.utils.Position;

/**
 * Sponge implementation of {@link Location}.
 */
public class SpongeLocation implements Location {
    private final org.spongepowered.api.world.Location location;

    /**
     * Creates a new location.
     */
    public SpongeLocation(org.spongepowered.api.world.Location location) {
        this.location = location;
    }

    /**
     * Getter for the Sponge location.
     * @return The Sponge location.
     */
    public org.spongepowered.api.world.Location getLocation() {
        return location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(double x) {
        // TODO: Check to see of Location.position() is mutable
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return location.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBlockX() {
        return location.getBlockX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(double y) {
        // TODO: Check to see of Location.position() is mutable
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return location.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBlockY() {
        return location.getBlockY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setZ(double z) {
        // TODO: Check to see of Location.position() is mutable
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getZ() {
        return location.getZ();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBlockZ() {
        return location.getBlockZ();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setYaw(float yaw) {
        // TODO: Find a way to set the yaw
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getYaw() {
        // TODO: Find a way to get the yaw
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPitch(float pitch) {
        // TODO: Find a way to set the pitch
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getPitch() {
        // TODO: Find a way to get the pitch
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getBlockPosition() {
        return new Position(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWorld(String world) {
        // TODO: Find a way to set the world
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWorld() {
        // TODO: Find a way to get the world
        return null;
    }
}
