/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16_5.sponge.world;

import dev.neuralnexus.taterapi.world.level.ServerLevel;

import org.jspecify.annotations.NonNull;

/** Sponge implementation of {@link ServerLevel}. */
public class SpongeServerWorld extends SpongeWorld implements ServerLevel {
    private final org.spongepowered.api.world.server.@NonNull ServerWorld level;

    public SpongeServerWorld(final org.spongepowered.api.world.server.@NonNull ServerWorld level) {
        super(level);
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public org.spongepowered.api.world.server.@NonNull ServerWorld level() {
        return this.level;
    }
}
