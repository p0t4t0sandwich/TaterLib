/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20.bukkit.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;

/** Bukkit implementation of {@link Location}. */
public class BukkitLocation implements Location {
    private final org.bukkit.Location location;

    /** Creates a new location. */
    public BukkitLocation(org.bukkit.Location location) {
        this.location = location;
    }

    @Override
    public void setX(double x) {
        location.setX(x);
    }

    @Override
    public void setY(double y) {
        location.setY(y);
    }

    @Override
    public void setZ(double z) {
        location.setZ(z);
    }

    @Override
    public float yaw() {
        return location.getYaw();
    }

    @Override
    public void setYaw(float yaw) {
        location.setYaw(yaw);
    }

    @Override
    public float pitch() {
        return location.getPitch();
    }

    @Override
    public void setPitch(float pitch) {
        location.setPitch(pitch);
    }

    @Override
    public BlockPos blockPosition() {
        return new BlockPos(location.getX(), location.getY(), location.getZ());
    }

    @Override
    public World world() {
        return new BukkitWorld(location.getWorld());
    }

    @Override
    public void setWorld(World world) {
        location.setWorld(((BukkitWorld) world).world());
    }
}
