package dev.neuralnexus.taterlib.v1_11.sponge.world;

import dev.neuralnexus.taterlib.world.ServerWorld;

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
