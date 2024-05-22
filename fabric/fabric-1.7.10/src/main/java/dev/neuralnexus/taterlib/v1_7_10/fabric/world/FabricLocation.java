package dev.neuralnexus.taterlib.v1_7_10.fabric.world;

import dev.neuralnexus.taterlib.world.BlockPos;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

import net.minecraft.entity.Entity;

/** Fabric implementation of {@link Location}. */
public class FabricLocation implements Location {
    private BlockPos blockPos;
    private float yaw;
    private float pitch;
    private net.minecraft.world.World world;

    /** Creates a new location from an Entity. */
    public FabricLocation(Entity entity) {
        this(
                new BlockPos(entity.x, entity.y, entity.z),
                (float) entity.getRotation().x,
                (float) entity.getRotation().y,
                entity.world);
    }

    /** Creates a new location. */
    public FabricLocation(
            BlockPos blockPos, float yaw, float pitch, net.minecraft.world.World world) {
        this.blockPos = blockPos;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    /** {@inheritDoc} */
    @Override
    public double x() {
        return blockPos.x();
    }

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        blockPos = new BlockPos(x, y(), z());
    }

    /** {@inheritDoc} */
    @Override
    public double blockX() {
        return Math.floor(x());
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return blockPos.y();
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        blockPos = new BlockPos(x(), y, z());
    }

    /** {@inheritDoc} */
    @Override
    public double blockY() {
        return Math.floor(y());
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return blockPos.z();
    }

    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        blockPos = new BlockPos(x(), y(), z);
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
    public BlockPos blockPosition() {
        return new BlockPos(blockX(), blockY(), blockZ());
    }

    /** {@inheritDoc} */
    @Override
    public World world() {
        return new FabricWorld(world);
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(World world) {
        this.world = ((FabricWorld) world).world();
    }
}
