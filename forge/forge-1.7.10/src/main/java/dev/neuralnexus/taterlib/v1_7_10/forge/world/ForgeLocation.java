/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.forge.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;

import net.minecraft.entity.Entity;

/** Forge implementation of {@link Location}. */
public class ForgeLocation implements Location {
    private BlockPos position;
    private float yaw;
    private float pitch;
    private net.minecraft.world.World world;

    /** Creates a new location from an Entity. */
    public ForgeLocation(Entity entity) {
        this(
                new BlockPos(entity.posX, entity.posY, entity.posZ),
                entity.rotationYaw,
                entity.rotationPitch,
                entity.worldObj);
    }

    /** Creates a new location. */
    public ForgeLocation(
            BlockPos position, float yaw, float pitch, net.minecraft.world.World world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    @Override
    public double x() {
        return position.x();
    }

    @Override
    public void setX(double x) {
        position = new BlockPos(x, y(), z());
    }

    @Override
    public double blockX() {
        return Math.floor(x());
    }

    @Override
    public double y() {
        return position.y();
    }

    @Override
    public void setY(double y) {
        position = new BlockPos(x(), y, z());
    }

    @Override
    public double blockY() {
        return Math.floor(y());
    }

    @Override
    public double z() {
        return position.z();
    }

    @Override
    public void setZ(double z) {
        position = new BlockPos(x(), y(), z);
    }

    @Override
    public double blockZ() {
        return Math.floor(z());
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
        return new ForgeWorld(world);
    }

    @Override
    public void setWorld(World world) {
        this.world = ((ForgeWorld) world).world();
    }
}
