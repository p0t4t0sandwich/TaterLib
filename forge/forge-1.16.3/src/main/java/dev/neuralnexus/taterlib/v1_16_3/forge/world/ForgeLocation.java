package dev.neuralnexus.taterlib.v1_16_3.forge.world;

import dev.neuralnexus.taterlib.world.BlockPos;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;

/** Forge implementation of {@link Location}. */
public class ForgeLocation implements Location {
    private Vector3d position;
    private float yaw;
    private float pitch;
    private net.minecraft.world.World world;

    /** Creates a new location from an Entity. */
    public ForgeLocation(Entity entity) {
        this(entity.position(), entity.xRot, entity.yRot, entity.level);
    }

    /** Creates a new location. */
    public ForgeLocation(
            Vector3d position, float yaw, float pitch, net.minecraft.world.World world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        position = new Vector3d(x, y(), z());
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        position = new Vector3d(x(), y, z());
    }

    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        position = new Vector3d(x(), y(), z);
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
        return new ForgeWorld(world);
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(World world) {
        this.world = ((ForgeWorld) world).world();
    }
}
