/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_2_5.bukkit.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;

import org.jspecify.annotations.NonNull;

/** Bukkit implementation of {@link Location}. */
public class BukkitLocation implements Location {
    private final org.bukkit.@NonNull Location location;

    /** Creates a new location. */
    public BukkitLocation(final org.bukkit.@NonNull Location location) {
        this.location = location;
    }

    @Override
    public void setX(final double x) {
        this.location.setX(x);
    }

    @Override
    public void setY(final double y) {
        this.location.setY(y);
    }

    @Override
    public void setZ(final double z) {
        this.location.setZ(z);
    }

    @Override
    public float yaw() {
        return this.location.getYaw();
    }

    @Override
    public void setYaw(final float yaw) {
        this.location.setYaw(yaw);
    }

    @Override
    public float pitch() {
        return this.location.getPitch();
    }

    @Override
    public void setPitch(final float pitch) {
        this.location.setPitch(pitch);
    }

    @Override
    public @NonNull BlockPos pos() {
        return new BlockPos(this.location.getX(), this.location.getY(), this.location.getZ());
    }

    @Override
    public @NonNull Level level() {
        return new BukkitWorld(this.location.getWorld());
    }

    @Override
    public void setLevel(final @NonNull Level level) {
        this.location.setWorld(((BukkitWorld) level).unwrap());
    }
}
