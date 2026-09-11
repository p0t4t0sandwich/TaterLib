/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;

import org.jspecify.annotations.NonNull;

/** Sponge implementation of {@link Location}. */
public class SpongeLocation implements Location {
    private final org.spongepowered.api.world.@NonNull Location<org.spongepowered.api.world.World>
            location;

    /** Creates a new location. */
    public SpongeLocation(
            final org.spongepowered.api.world.@NonNull Location<org.spongepowered.api.world.World>
                    location) {
        this.location = location;
    }

    @Override
    public void setX(final double x) {
        this.location.add(x - location.getX(), 0, 0);
    }

    @Override
    public void setY(final double y) {
        this.location.add(0, y - location.getY(), 0);
    }

    @Override
    public void setZ(final double z) {
        this.location.add(0, 0, z - location.getZ());
    }

    @Override
    public float yaw() {
        // TODO: Find a way to get the yaw
        return 0;
    }

    @Override
    public void setYaw(final float yaw) {
        // TODO: Find a way to set the yaw
    }

    @Override
    public float pitch() {
        // TODO: Find a way to get the pitch
        return 0;
    }

    @Override
    public void setPitch(final float pitch) {
        // TODO: Find a way to set the pitch
    }

    @Override
    public @NonNull BlockPos pos() {
        return new BlockPos(this.location.getX(), this.location.getY(), this.location.getZ());
    }

    @Override
    public @NonNull Level level() {
        return new SpongeWorld(this.location.getExtent());
    }

    @Override
    public void setLevel(final @NonNull Level level) {
        this.location.setExtent(((SpongeWorld) level).unwrap());
    }
}
