/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.sponge.world;

import dev.neuralnexus.taterapi.world.ServerWorld;

import org.spongepowered.api.world.World;

/** Sponge implementation of {@link ServerWorld}. */
public class SpongeServerWorld extends SpongeWorld implements ServerWorld {
    private final World level;

    public SpongeServerWorld(World level) {
        super(level);
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public World world() {
        return level;
    }
}
