package dev.neuralnexus.taterlib.bukkit.util;

import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.common.utils.Position;

/**
 * Bukkit implementation of {@link Location}.
 */
public class BukkitLocation implements Location {
    private final org.bukkit.Location location;

    /**
     * Creates a new location.
     */
    public BukkitLocation(org.bukkit.Location location) {
        this.location = location;
    }

    /**
     * Getter for the bukkit location.
     * @return The bukkit location.
     */
    public org.bukkit.Location getLocation() {
        return location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(double x) {
        location.setX(x);
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
        location.setY(y);
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
        location.setZ(z);
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
        location.setYaw(yaw);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getYaw() {
        return location.getYaw();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPitch(float pitch) {
        location.setPitch(pitch);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getPitch() {
        return location.getPitch();
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
        location.setWorld(org.bukkit.Bukkit.getWorld(world));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWorld() {
        if (location.getWorld() == null) {
            return "";
        }
        return location.getWorld().getName();
    }
}
