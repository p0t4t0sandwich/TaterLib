/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16_5.sponge.world;

import dev.neuralnexus.taterapi.world.ServerWorld;

/** Sponge implementation of {@link ServerWorld}. */
public class SpongeServerWorld extends SpongeWorld implements ServerWorld {
    private final org.spongepowered.api.world.server.ServerWorld level;

    public SpongeServerWorld(org.spongepowered.api.world.server.ServerWorld level) {
        super(level);
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public org.spongepowered.api.world.server.ServerWorld world() {
        return level;
    }
}
