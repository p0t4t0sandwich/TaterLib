/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.world;

import dev.neuralnexus.taterapi.world.ServerWorld;

/** Vanilla implementation of {@link ServerWorld}. */
public class WrappedServerWorld extends WrappedWorld implements ServerWorld {
    private final net.minecraft.server.world.ServerWorld level;

    public WrappedServerWorld(net.minecraft.server.world.ServerWorld level) {
        super(level);
        this.level = level;
    }

    @Override
    public net.minecraft.server.world.ServerWorld unwrap() {
        return this.level;
    }
}
