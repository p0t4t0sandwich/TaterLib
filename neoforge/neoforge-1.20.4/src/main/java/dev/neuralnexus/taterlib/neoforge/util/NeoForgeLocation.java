package dev.neuralnexus.taterlib.neoforge.util;

import dev.neuralnexus.taterlib.neoforge.entity.NeoForgeEntity;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.utils.Position;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

/** Fabric implementation of {@link Location}. */
public class NeoForgeLocation implements Location {
    private Vec3 position;
    private float yaw;
    private float pitch;
    private String world;

    /** Creates a new location from an Entity. */
    public NeoForgeLocation(Entity entity) {
        this(
                entity.position(),
                entity.getXRot(),
                entity.getYRot(),
                new NeoForgeEntity(entity).getDimension());
    }

    /** Creates a new location. */
    public NeoForgeLocation(Vec3 position, float yaw, float pitch, String world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    /**
     * Getter for the Fabric location.
     *
     * @return The Fabric location.
     */
    public Vec3 getLocation() {
        return position;
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return position.x;
    }

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        position = new Vec3(x, getY(), getZ());
    }

    /** {@inheritDoc} */
    @Override
    public double getBlockX() {
        return Math.floor(getX());
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return position.y;
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        position = new Vec3(getX(), y, getZ());
    }

    /** {@inheritDoc} */
    @Override
    public double getBlockY() {
        return Math.floor(getY());
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return position.z;
    }

    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        position = new Vec3(getX(), getY(), z);
    }

    /** {@inheritDoc} */
    @Override
    public double getBlockZ() {
        return Math.floor(getZ());
    }

    /** {@inheritDoc} */
    @Override
    public float getYaw() {
        return yaw;
    }

    /** {@inheritDoc} */
    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        return pitch;
    }

    /** {@inheritDoc} */
    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /** {@inheritDoc} */
    @Override
    public Position getBlockPosition() {
        return new Position(getBlockX(), getBlockY(), getBlockZ());
    }

    /** {@inheritDoc} */
    @Override
    public String getWorld() {
        return world;
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(String world) {
        this.world = world;
    }
}
