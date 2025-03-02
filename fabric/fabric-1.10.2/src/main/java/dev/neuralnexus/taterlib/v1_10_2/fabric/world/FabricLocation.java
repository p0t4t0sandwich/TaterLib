/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_10_2.fabric.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;

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
        this(entity.getPos(), entity.yaw, entity.pitch, entity.world);
    }

    /** Creates a new location. */
    public FabricLocation(Vec3d position, float yaw, float pitch, net.minecraft.world.World world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    @Override
    public void setX(double x) {
        position = new Vec3d(x, y(), z());
    }

    @Override
    public void setY(double y) {
        position = new Vec3d(x(), y, z());
    }

    @Override
    public void setZ(double z) {
        position = new Vec3d(x(), y(), z);
    }

    @Override
    public float yaw() {
        return yaw;
    }

    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    @Override
    public float pitch() {
        return pitch;
    }

    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public BlockPos blockPosition() {
        return new BlockPos(position.x, position.y, position.z);
    }

    @Override
    public World world() {
        return new FabricWorld(world);
    }

    @Override
    public void setWorld(World world) {
        this.world = ((FabricWorld) world).unwrap();
    }
}
