/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.bukkit.world;

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

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        location.setX(x);
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        location.setY(y);
    }

    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        location.setZ(z);
    }

    /** {@inheritDoc} */
    @Override
    public float yaw() {
        return location.getYaw();
    }

    /** {@inheritDoc} */
    @Override
    public void setYaw(float yaw) {
        location.setYaw(yaw);
    }

    /** {@inheritDoc} */
    @Override
    public float pitch() {
        return location.getPitch();
    }

    /** {@inheritDoc} */
    @Override
    public void setPitch(float pitch) {
        location.setPitch(pitch);
    }

    /** {@inheritDoc} */
    @Override
    public BlockPos blockPosition() {
        return new BlockPos(location.getX(), location.getY(), location.getZ());
    }

    /** {@inheritDoc} */
    @Override
    public World world() {
        return new BukkitWorld(location.getWorld());
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(World world) {
        location.setWorld(((BukkitWorld) world).world());
    }
}
