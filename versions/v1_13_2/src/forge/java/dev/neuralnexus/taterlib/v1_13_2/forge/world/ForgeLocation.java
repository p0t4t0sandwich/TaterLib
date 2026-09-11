/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.ForgeEntity;

import net.minecraft.util.math.Vec3d;

import org.jspecify.annotations.NonNull;

/** Forge implementation of {@link Location}. */
public class ForgeLocation implements Location {
    private @NonNull BlockPos position;
    private float yaw;
    private float pitch;
    private @NonNull Level level;

    /** Creates a new location from an Entity. */
    public ForgeLocation(final @NonNull Entity entity) {
        this(((ForgeEntity) entity).unwrap());
    }

    /** Creates a new location from an Entity. */
    public ForgeLocation(final net.minecraft.entity.@NonNull Entity entity) {
        final Vec3d pos = entity.getPositionVector();
        this(
                new BlockPos(pos.x, pos.y, pos.z),
                entity.rotationYaw,
                entity.rotationPitch,
                new ForgeWorld(entity.world));
    }

    /** Creates a new location. */
    public ForgeLocation(
            final @NonNull BlockPos position,
            final float yaw,
            final float pitch,
            final @NonNull Level level) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.level = level;
    }

    @Override
    public void setX(final double x) {
        this.position = new BlockPos(x, this.y(), this.z());
    }

    @Override
    public void setY(final double y) {
        this.position = new BlockPos(this.x(), y, this.z());
    }

    @Override
    public void setZ(final double z) {
        this.position = new BlockPos(this.x(), this.y(), z);
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
        return this.position;
    }

    @Override
    public @NonNull Level level() {
        return this.level;
    }

    @Override
    public void setLevel(final @NonNull Level level) {
        this.level = level;
    }
}
