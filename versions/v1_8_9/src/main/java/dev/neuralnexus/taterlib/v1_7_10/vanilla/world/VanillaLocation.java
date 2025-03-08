/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.VanillaFactories;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

/** Vanilla implementation of {@link Location}. */
public class VanillaLocation implements Location {
    private Vec3 position;
    private float yaw;
    private float pitch;
    private net.minecraft.world.World world;

    public VanillaLocation(Entity entity) {
        this(
                VanillaFactories.vec3.create(entity.posX, entity.posY, entity.posZ),
                entity.rotationYaw,
                entity.rotationPitch,
                entity.worldObj);
    }

    public VanillaLocation(Vec3 position, float yaw, float pitch, net.minecraft.world.World world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    @Override
    public double x() {
        return position.xCoord;
    }

    @Override
    public void setX(double x) {
        position = VanillaFactories.vec3.create(x, y(), z());
    }

    @Override
    public double blockX() {
        return Math.floor(x());
    }

    @Override
    public double y() {
        return position.yCoord;
    }

    @Override
    public void setY(double y) {
        position = VanillaFactories.vec3.create(x(), y, z());
    }

    @Override
    public double blockY() {
        return Math.floor(y());
    }

    @Override
    public double z() {
        return position.zCoord;
    }

    @Override
    public void setZ(double z) {
        position = VanillaFactories.vec3.create(x(), y(), z);
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
        return new BlockPos(position.xCoord, position.yCoord, position.zCoord);
    }

    @Override
    public World world() {
        return new WrappedWorld(world);
    }

    @Override
    public void setWorld(World world) {
        this.world = ((WrappedWorld) world).unwrap();
    }

    public static class Builder implements Location.Builder {
        private Vec3 position = VanillaFactories.vec3.create(0, 0, 0);
        private float yaw = 0;
        private float pitch = 0;
        private net.minecraft.world.World world = null;

        @Override
        public Builder from(Location location) {
            this.position =
                    VanillaFactories.vec3.create(location.x(), location.y(), location.z());
            this.yaw = location.yaw();
            this.pitch = location.pitch();
            this.world = ((WrappedWorld) location.world()).unwrap();
            return this;
        }

        @Override
        public Builder position(BlockPos position) {
            this.position =
                    VanillaFactories.vec3.create(position.x(), position.y(), position.z());
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
