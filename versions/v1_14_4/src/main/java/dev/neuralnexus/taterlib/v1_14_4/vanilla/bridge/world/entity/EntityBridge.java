/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity;

import dev.neuralnexus.taterapi.world.Location;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;

public interface EntityBridge {
    void bridge$remove();

    ResourceLocation bridge$type();

    ResourceLocation bridge$biome();

    void bridge$changeDimension(ServerLevel level, Location location);
}
