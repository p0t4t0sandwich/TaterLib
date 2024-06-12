package dev.neuralnexus.taterlib.v1_15.fabric.world;

import dev.neuralnexus.taterlib.world.BlockPos;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

/** Fabric implementation of {@link Location}. */
public class FabricLocation implements Location {
    private Vec3d position;
    private float yaw;
    private float pitch;
    private net.minecraft.world.World world;

    /** Creates a new location from an Entity. */
    public FabricLocation(Entity entity) {
        this(entity.getPos(), entity.getYaw(0.0F), entity.getPitch(0.0F), entity.world);
    }

    /** Creates a new location. */
    public FabricLocation(Vec3d position, float yaw, float pitch, net.minecraft.world.World world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        position = new Vec3d(x, y(), z());
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        position = new Vec3d(x(), y, z());
    }
    
    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        position = new Vec3d(x(), y(), z);
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
        return new BlockPos(position.x, position.y, position.z);
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
