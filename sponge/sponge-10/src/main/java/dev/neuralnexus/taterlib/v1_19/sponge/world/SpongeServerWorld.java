package dev.neuralnexus.taterlib.v1_19.sponge.world;

import dev.neuralnexus.taterlib.world.ServerWorld;

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
