/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.world;

import dev.neuralnexus.taterapi.world.level.ServerLevel;

import org.jspecify.annotations.NonNull;

/** Vanilla implementation of {@link ServerLevel}. */
public class VanillaServerWorld extends VanillaWorld implements ServerLevel {
    private final net.minecraft.server.level.@NonNull ServerLevel level;

    public VanillaServerWorld(final net.minecraft.server.level.@NonNull ServerLevel level) {
        super(level);
        this.level = level;
    }

    @Override
    public net.minecraft.server.level.@NonNull ServerLevel unwrap() {
        return this.level;
    }
}
