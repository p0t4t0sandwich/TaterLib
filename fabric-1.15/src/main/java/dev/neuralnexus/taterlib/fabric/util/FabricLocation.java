package dev.neuralnexus.taterlib.fabric.util;

import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.common.utils.Position;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

/**
 * Fabric implementation of {@link Location}.
 */
public class FabricLocation implements Location {
    private Vec3d position;
    private float yaw;
    private float pitch;
    private String world;


    /**
     * Creates a new location from an Entity.
     */
    public FabricLocation(Entity entity) {
        this(entity.getPos(), entity.getYaw(0.0F), entity.getPitch(0.0F), entity.getEntityWorld().getDimension().getType().toString());
    }

    /**
     * Creates a new location.
     */
    public FabricLocation(Vec3d position, float yaw, float pitch, String world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    /**
     * Getter for the Fabric location.
     * @return The Fabric location.
     */
    public Vec3d getLocation() {
        return position;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setX(double x) {
        position = new Vec3d(x, getY(), getZ());
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getX() {
        return position.getX();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getBlockX() {
        return Math.floor(getX());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setY(double y) {
        position = new Vec3d(getX(), y, getZ());
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getY() {
        return position.getY();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getBlockY() {
        return Math.floor(getY());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setZ(double z) {
        position = new Vec3d(getX(), getY(), z);
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getZ() {
        return position.getZ();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getBlockZ() {
        return Math.floor(getZ());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /**
     * @inheritDoc
     */
    @Override
    public float getYaw() {
        return yaw;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /**
     * @inheritDoc
     */
    @Override
    public float getPitch() {
        return pitch;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Position getBlockPosition() {
        return new Position(getBlockX(), getBlockY(), getBlockZ());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setWorld(String world) {
        this.world = world;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getWorld() {
        return world;
    }
}
