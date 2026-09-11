/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.forge.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import org.jspecify.annotations.NonNull;

/** Forge implementation of {@link Location}. */
public class ForgeLocation implements Location {
    private @NonNull BlockPos position;
    private float yaw;
    private float pitch;
    private @NonNull World level;

    /** Creates a new location from an Entity. */
    public ForgeLocation(final @NonNull Entity entity) {
        this(
                new BlockPos(entity.posX, entity.posY, entity.posZ),
                entity.rotationYaw,
                entity.rotationPitch,
                entity.worldObj);
    }

    /** Creates a new location. */
    public ForgeLocation(
            final @NonNull BlockPos position,
            final float yaw,
            final float pitch,
            final @NonNull World world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.level = world;
    }

    @Override
    public void setX(final double x) {
        position = new BlockPos(x, y(), z());
    }

    @Override
    public void setY(double y) {
        position = new BlockPos(x(), y, z());
    }

    @Override
    public void setZ(final double z) {
        position = new BlockPos(x(), y(), z);
    }

    @Override
    public float yaw() {
        return this.yaw;
    }

    @Override
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }

    @Override
    public float pitch() {
        return this.pitch;
    }

    @Override
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }

    @Override
    public @NonNull BlockPos pos() {
        return new BlockPos(this.position.x(), this.position.y(), this.position.z());
    }

    @Override
    public @NonNull Level level() {
        return new ForgeWorld(this.level);
    }

    @Override
    public void setLevel(final @NonNull Level level) {
        this.level = ((ForgeWorld) level).unwrap();
    }
}
