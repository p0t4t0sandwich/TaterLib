/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.fabric.world;

import dev.neuralnexus.taterapi.world.ServerWorld;

/** Fabric implementation of {@link ServerWorld}. */
public class FabricServerWorld extends FabricWorld implements ServerWorld {
    private final net.minecraft.server.world.ServerWorld level;

    public FabricServerWorld(net.minecraft.server.world.ServerWorld level) {
        super(level);
        this.level = level;
    }

    @Override
    public net.minecraft.server.world.ServerWorld unwrap() {
        return this.level;
    }
}
