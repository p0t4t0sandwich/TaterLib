package dev.neuralnexus.taterlib.v1_21.vanilla.world;

import dev.neuralnexus.taterlib.world.ServerWorld;

import net.minecraft.server.level.ServerLevel;

/** Vanilla implementation of {@link ServerWorld}. */
public class VanillaServerWorld extends VanillaWorld implements ServerWorld {
    private final ServerLevel level;

    public VanillaServerWorld(ServerLevel level) {
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
