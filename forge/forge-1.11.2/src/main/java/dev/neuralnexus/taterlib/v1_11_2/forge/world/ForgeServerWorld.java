package dev.neuralnexus.taterlib.v1_11_2.forge.world;

import dev.neuralnexus.taterlib.world.ServerWorld;
import net.minecraft.world.WorldServer;

/** Forge implementation of {@link ServerWorld}. */
public class ForgeServerWorld extends ForgeWorld implements ServerWorld {
    private final WorldServer level;

    public ForgeServerWorld(WorldServer level) {
        super(level);
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public WorldServer world() {
        return level;
    }
}
