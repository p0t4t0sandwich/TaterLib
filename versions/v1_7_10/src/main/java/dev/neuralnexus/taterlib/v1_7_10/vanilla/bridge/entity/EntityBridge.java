/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity;

import dev.neuralnexus.taterapi.resources.Identifier;

import net.minecraft.server.world.ServerWorld;

public interface EntityBridge {
    Identifier bridge$biome();

    void bridge$teleportToDimension(ServerWorld world);
}
