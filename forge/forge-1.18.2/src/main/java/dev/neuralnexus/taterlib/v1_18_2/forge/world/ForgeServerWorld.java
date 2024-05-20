package dev.neuralnexus.taterlib.v1_18_2.forge.world;

import dev.neuralnexus.taterlib.world.ServerWorld;

import net.minecraft.server.level.ServerLevel;

/** Forge implementation of {@link ServerWorld}. */
public class ForgeServerWorld extends ForgeWorld implements ServerWorld {
    private final ServerLevel level;

    public ForgeServerWorld(ServerLevel level) {
        super(level);
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public ServerLevel world() {
        return level;
    }
}
