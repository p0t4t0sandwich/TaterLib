package dev.neuralnexus.taterlib.v1_19.vanilla.util;

import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.utils.Position;
import dev.neuralnexus.taterlib.v1_19.vanilla.entity.VanillaEntity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

/** Vanilla implementation of {@link Location}. */
public class VanillaLocation implements Location {
    private Vec3 position;
    private float yaw;
    private float pitch;
    private String world;

    /** Creates a new location from an Entity. */
    public VanillaLocation(Entity entity) {
        this(
                entity.position(),
                entity.getXRot(),
                entity.getYRot(),
                new VanillaEntity(entity).dimension());
    }

    /** Creates a new location. */
    public VanillaLocation(Vec3 position, float yaw, float pitch, String world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    /** {@inheritDoc} */
    @Override
    public double x() {
        return position.x;
    }

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        position = new Vec3(x, y(), z());
    }

    /** {@inheritDoc} */
    @Override
    public double blockX() {
        return Math.floor(x());
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return position.y;
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        position = new Vec3(x(), y, z());
    }

    /** {@inheritDoc} */
    @Override
    public double blockY() {
        return Math.floor(y());
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return position.z;
    }

    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        position = new Vec3(x(), y(), z);
    }

    /** {@inheritDoc} */
    @Override
    public double blockZ() {
        return Math.floor(z());
    }

    /** {@inheritDoc} */
    @Override
    public float yaw() {
        return yaw;
    }

    /** {@inheritDoc} */
    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /** {@inheritDoc} */
    @Override
    public float pitch() {
        return pitch;
    }

    /** {@inheritDoc} */
    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /** {@inheritDoc} */
    @Override
    public Position blockPosition() {
        return new Position(blockX(), blockY(), blockZ());
    }

    /** {@inheritDoc} */
    @Override
    public String world() {
        return world;
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(String world) {
        this.world = world;
    }
}
