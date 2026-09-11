/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16_5.sponge.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;

import org.jspecify.annotations.NonNull;

/** Sponge implementation of {@link Location}. */
@SuppressWarnings("FieldCanBeLocal")
public class SpongeLocation implements Location {
    private final org.spongepowered.api.world.server.ServerLocation location;
    private org.spongepowered.api.world.server.ServerWorld level;

    /** Creates a new location. */
    public SpongeLocation(
            final org.spongepowered.api.world.server.@NonNull ServerLocation location) {
        this.location = location;
    }

    @Override
    public void setX(final double x) {
        this.location.add(x - location.x(), 0, 0);
    }

    @Override
    public void setY(final double y) {
        this.location.add(0, y - location.y(), 0);
    }

    @Override
    public void setZ(final double z) {
        this.location.add(0, 0, z - location.z());
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
        return new BlockPos(this.location.blockX(), this.location.blockY(), this.location.blockZ());
    }

    @Override
    public @NonNull Level level() {
        if (this.level != null) return new SpongeWorld(this.level);
        return new SpongeWorld(this.location.world());
    }

    @Override
    public void setLevel(final @NonNull Level level) {
        this.level = ((SpongeServerWorld) level).level();
    }
}
