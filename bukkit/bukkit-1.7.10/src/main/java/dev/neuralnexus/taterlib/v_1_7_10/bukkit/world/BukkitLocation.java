package dev.neuralnexus.taterlib.v_1_7_10.bukkit.world;

import dev.neuralnexus.taterlib.world.BlockPos;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

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
    public BlockPos blockPosition() {
        return new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /** {@inheritDoc} */
    @Override
    public World world() {
        return new BukkitWorld(location.getWorld());
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(World world) {
        location.setWorld(((BukkitWorld) world).world());
    }
}
