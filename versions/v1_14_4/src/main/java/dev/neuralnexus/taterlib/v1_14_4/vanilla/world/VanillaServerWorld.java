/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.world;

import dev.neuralnexus.taterapi.world.ServerWorld;

import net.minecraft.server.level.ServerLevel;

/** Vanilla implementation of {@link ServerWorld}. */
public class VanillaServerWorld extends VanillaWorld implements ServerWorld {
    private final ServerLevel level;

    public VanillaServerWorld(ServerLevel level) {
        super(level);
        this.level = level;
    }

    @Override
    public ServerLevel unwrap() {
        return this.level;
    }
}
