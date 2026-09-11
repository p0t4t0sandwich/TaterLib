/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.VanillaFactories;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import org.jspecify.annotations.NonNull;

/** Vanilla implementation of {@link Location}. */
public class VanillaLocation implements Location {
    private @NonNull Vec3d position;
    private float yaw;
    private float pitch;
    private @NonNull World level;

    public VanillaLocation(final @NonNull Entity entity) {
        this(
                VanillaFactories.vec3.create(entity.x, entity.y, entity.z),
                entity.yaw,
                entity.pitch,
                entity.world);
    }

    public VanillaLocation(
            final @NonNull Vec3d position,
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
        this.position = VanillaFactories.vec3.create(x, this.y(), this.z());
    }

    @Override
    public void setY(final double y) {
        this.position = VanillaFactories.vec3.create(this.x(), y, this.z());
    }

    @Override
    public void setZ(final double z) {
        this.position = VanillaFactories.vec3.create(this.x(), this.y(), z);
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
        return new WrappedWorld(this.level);
    }

    @Override
    public void setLevel(final @NonNull Level level) {
        this.level = ((WrappedWorld) level).unwrap();
    }

    public static class Builder implements Location.Builder {
        private Vec3d position = VanillaFactories.vec3.create(0, 0, 0);
        private float yaw = 0;
        private float pitch = 0;
        private World level = null;

        @Override
        public Builder from(final @NonNull Location location) {
            this.position = VanillaFactories.vec3.create(location.x(), location.y(), location.z());
            this.yaw = location.yaw();
            this.pitch = location.pitch();
            this.level = ((WrappedWorld) location.level()).unwrap();
            return this;
        }

        @Override
        public Builder position(final @NonNull BlockPos position) {
            this.position = VanillaFactories.vec3.create(position.x(), position.y(), position.z());
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
            this.level = ((WrappedWorld) level).unwrap();
            return this;
        }

        @Override
        public @NonNull Location build() {
            return new VanillaLocation(this.position, this.yaw, this.pitch, this.level);
        }
    }
}
