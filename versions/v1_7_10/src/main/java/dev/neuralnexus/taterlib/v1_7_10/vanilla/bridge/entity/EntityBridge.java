/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity;

import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.server.world.ServerWorld;

public interface EntityBridge {
    ResourceKey bridge$biome();

    void bridge$teleportToDimension(ServerWorld world);
}
