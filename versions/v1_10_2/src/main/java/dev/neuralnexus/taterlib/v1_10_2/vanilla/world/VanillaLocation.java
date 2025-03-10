/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_10_2.vanilla.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.VanillaFactories;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

/** Vanilla implementation of {@link Location}. */
public class VanillaLocation implements Location {
    private Vec3d position;
    private float yaw;
    private float pitch;
    private net.minecraft.world.World world;

    public VanillaLocation(Entity entity) {
        this(
                VanillaFactories.vec3.create(entity.x, entity.y, entity.z),
                entity.yaw,
                entity.pitch,
                entity.world);
    }

    public VanillaLocation(
            Vec3d position, float yaw, float pitch, net.minecraft.world.World world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    @Override
    public double x() {
        return this.position.x;
    }

    @Override
    public void setX(double x) {
        position = VanillaFactories.vec3.create(x, this.y(), this.z());
    }

    @Override
    public double blockX() {
        return Math.floor(this.x());
    }

    @Override
    public double y() {
        return this.position.y;
    }

    @Override
    public void setY(double y) {
        this.position = VanillaFactories.vec3.create(this.x(), y, this.z());
    }

    @Override
    public double blockY() {
        return Math.floor(this.y());
    }

    @Override
    public double z() {
        return this.position.z;
    }

    @Override
    public void setZ(double z) {
        this.position = VanillaFactories.vec3.create(this.x(), this.y(), z);
    }

    @Override
    public double blockZ() {
        return Math.floor(this.z());
    }

    @Override
    public float yaw() {
        return this.yaw;
    }

    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    @Override
    public float pitch() {
        return this.pitch;
    }

    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public BlockPos blockPosition() {
        return new BlockPos(this.position.x, this.position.y, this.position.z);
    }

    @Override
    public World world() {
        return new WrappedWorld(this.world);
    }

    @Override
    public void setWorld(World world) {
        this.world = ((WrappedWorld) world).unwrap();
    }

    public static class Builder implements Location.Builder {
        private Vec3d position = VanillaFactories.vec3.create(0, 0, 0);
        private float yaw = 0;
        private float pitch = 0;
        private net.minecraft.world.World world = null;

        @Override
        public Builder from(Location location) {
            this.position = VanillaFactories.vec3.create(location.x(), location.y(), location.z());
            this.yaw = location.yaw();
            this.pitch = location.pitch();
            this.world = ((WrappedWorld) location.world()).unwrap();
            return this;
        }

        @Override
        public Builder position(BlockPos position) {
            this.position = VanillaFactories.vec3.create(position.x(), position.y(), position.z());
            return this;
        }

        @Override
        public Builder yaw(float yaw) {
            this.yaw = yaw;
            return this;
        }

        @Override
        public Builder pitch(float pitch) {
            this.pitch = pitch;
            return this;
        }

        @Override
        public Builder world(World world) {
            this.world = ((WrappedWorld) world).unwrap();
            return this;
        }

        @Override
        public Location build() {
            return new VanillaLocation(position, yaw, pitch, world);
        }
    }
}
