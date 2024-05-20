package dev.neuralnexus.taterlib.v1_14_4.forge.world;

import dev.neuralnexus.taterlib.world.ServerWorld;

/** Forge implementation of {@link ServerWorld}. */
public class ForgeServerWorld extends ForgeWorld implements ServerWorld {
    private final net.minecraft.world.server.ServerWorld level;

    public ForgeServerWorld(net.minecraft.world.server.ServerWorld level) {
        super(level);
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public net.minecraft.world.server.ServerWorld world() {
        return level;
    }
}
