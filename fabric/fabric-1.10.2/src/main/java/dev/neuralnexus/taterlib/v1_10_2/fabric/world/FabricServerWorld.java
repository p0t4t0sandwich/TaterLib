package dev.neuralnexus.taterlib.v1_10_2.fabric.world;

import dev.neuralnexus.taterlib.world.ServerWorld;

/** Fabric implementation of {@link ServerWorld}. */
public class FabricServerWorld extends FabricWorld implements ServerWorld {
    private final net.minecraft.server.world.ServerWorld level;

    public FabricServerWorld(net.minecraft.server.world.ServerWorld level) {
        super(level);
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public net.minecraft.server.world.ServerWorld world() {
        return level;
    }
}
