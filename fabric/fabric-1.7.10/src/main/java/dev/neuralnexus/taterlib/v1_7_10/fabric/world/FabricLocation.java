/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.fabric.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;

import net.minecraft.entity.Entity;

/** Fabric implementation of {@link Location}. */
public class FabricLocation implements Location {
    private BlockPos position;
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
            BlockPos position, float yaw, float pitch, net.minecraft.world.World world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    @Override
    public void setX(double x) {
        position = new BlockPos(x, y(), z());
    }

    @Override
    public void setY(double y) {
        position = new BlockPos(x(), y, z());
    }

    @Override
    public void setZ(double z) {
        position = new BlockPos(x(), y(), z);
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
        return new BlockPos(position.x(), position.y(), position.z());
    }

    @Override
    public World world() {
        return new FabricWorld(world);
    }

    @Override
    public void setWorld(World world) {
        this.world = ((FabricWorld) world).world();
    }
}
