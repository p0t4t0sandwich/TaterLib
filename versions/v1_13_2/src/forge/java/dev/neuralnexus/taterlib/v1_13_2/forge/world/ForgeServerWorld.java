/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.world;

import dev.neuralnexus.taterapi.world.ServerWorld;

import net.minecraft.world.WorldServer;

/** Forge implementation of {@link ServerWorld}. */
public class ForgeServerWorld extends ForgeWorld implements ServerWorld {
    private final WorldServer level;

    public ForgeServerWorld(WorldServer level) {
        super(level);
        this.level = level;
    }

    @Override
    public WorldServer unwrap() {
        return this.level;
    }
}
