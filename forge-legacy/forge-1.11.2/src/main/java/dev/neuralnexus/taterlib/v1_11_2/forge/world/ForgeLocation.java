package dev.neuralnexus.taterlib.v1_11_2.forge.world;

import dev.neuralnexus.taterlib.world.BlockPos;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

/** Forge implementation of {@link Location}. */
public class ForgeLocation implements Location {
    private Vec3d position;
    private float yaw;
    private float pitch;
    private net.minecraft.world.World world;

    /** Creates a new location from an Entity. */
    public ForgeLocation(Entity entity) {
        this(entity.getPositionVector(), entity.rotationYaw, entity.rotationPitch, entity.world);
    }

    /** Creates a new location. */
    public ForgeLocation(Vec3d position, float yaw, float pitch, net.minecraft.world.World world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    /** {@inheritDoc} */
    @Override
    public double x() {
        return position.xCoord;
    }

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        position = new Vec3d(x, y(), z());
    }

    /** {@inheritDoc} */
    @Override
    public double blockX() {
        return Math.floor(x());
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return position.yCoord;
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        position = new Vec3d(x(), y, z());
    }

    /** {@inheritDoc} */
    @Override
    public double blockY() {
        return Math.floor(y());
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return position.zCoord;
    }

    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        position = new Vec3d(x(), y(), z);
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
        return new ForgeWorld(world);
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(World world) {
        this.world = ((ForgeWorld) world).world();
    }
}
