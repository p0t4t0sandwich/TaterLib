package dev.neuralnexus.taterlib.bukkit.util;

import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.utils.Position;

/** Bukkit implementation of {@link Location}. */
public class BukkitLocation implements Location {
    private final org.bukkit.Location location;

    /** Creates a new location. */
    public BukkitLocation(org.bukkit.Location location) {
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
        location.setX(x);
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
        location.setY(y);
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
        location.setZ(z);
    }

    /** {@inheritDoc} */
    @Override
    public double blockZ() {
        return location.getBlockZ();
    }

    /** {@inheritDoc} */
    @Override
    public float yaw() {
        return location.getYaw();
    }

    /** {@inheritDoc} */
    @Override
    public void setYaw(float yaw) {
        location.setYaw(yaw);
    }

    /** {@inheritDoc} */
    @Override
    public float pitch() {
        return location.getPitch();
    }

    /** {@inheritDoc} */
    @Override
    public void setPitch(float pitch) {
        location.setPitch(pitch);
    }

    /** {@inheritDoc} */
    @Override
    public Position blockPosition() {
        return new Position(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /** {@inheritDoc} */
    @Override
    public String world() {
        if (location.getWorld() == null) {
            return "";
        }
        return location.getWorld().getName();
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(String world) {
        location.setWorld(org.bukkit.Bukkit.getWorld(world));
    }
}
