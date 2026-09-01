/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
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
