/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.NonNull;

/** Vanilla implementation of {@link Location}. */
public class VanillaLocation implements Location {
    private Vec3 position;
    private float yaw;
    private float pitch;
    private net.minecraft.world.level.@NonNull Level level;

    /** Creates a new location from an Entity. */
    public VanillaLocation(final @NonNull Entity entity) {
        this(
                entity.position(),
                entity.getRotationVector().x,
                entity.getRotationVector().y,
                entity.level);
    }

    /** Creates a new location. */
    public VanillaLocation(
            final @NonNull Vec3 position,
            final float yaw,
            final float pitch,
            final net.minecraft.world.level.@NonNull Level level) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.level = level;
    }

    /**
     * Creates a new location builder.
     *
     * @return The builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public void setX(final double x) {
        position = new Vec3(x, y(), z());
    }

    @Override
    public void setY(final double y) {
        position = new Vec3(x(), y, z());
    }

    @Override
    public void setZ(final double z) {
        position = new Vec3(x(), y(), z);
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
        return new BlockPos(this.position.x, this.position.y, this.position.z);
    }

    @Override
    public @NonNull Level level() {
        return new VanillaWorld(this.level);
    }

    @Override
    public void setLevel(final @NonNull Level level) {
        this.level = ((VanillaWorld) level).unwrap();
    }

    public static class Builder implements Location.Builder {
        private Vec3 position = Vec3.ZERO;
        private float yaw = 0;
        private float pitch = 0;
        private net.minecraft.world.level.Level level = null;

        @Override
        public Builder from(final @NonNull Location location) {
            this.position = new Vec3(location.x(), location.y(), location.z());
            this.yaw = location.yaw();
            this.pitch = location.pitch();
            this.level = ((VanillaWorld) location.level()).unwrap();
            return this;
        }

        @Override
        public Builder position(final @NonNull BlockPos position) {
            this.position = new Vec3(position.x(), position.y(), position.z());
            return this;
        }

        @Override
        public Builder yaw(final float yaw) {
            this.yaw = yaw;
            return this;
        }

        @Override
        public Builder pitch(final float pitch) {
            this.pitch = pitch;
            return this;
        }

        @Override
        public Builder level(final @NonNull Level level) {
            this.level = ((VanillaWorld) level).unwrap();
            return this;
        }

        @Override
        public @NonNull Location build() {
            return new VanillaLocation(this.position, this.yaw, this.pitch, this.level);
        }
    }
}
