/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.world;

import dev.neuralnexus.taterapi.world.level.ServerLevel;

import net.minecraft.server.world.ServerWorld;

import org.jspecify.annotations.NonNull;

/** Vanilla implementation of {@link ServerLevel}. */
public class WrappedServerWorld extends WrappedWorld implements ServerLevel {
    private final @NonNull ServerWorld level;

    public WrappedServerWorld(final @NonNull ServerWorld level) {
        super(level);
        this.level = level;
    }

    @Override
    public final @NonNull ServerWorld unwrap() {
        return this.level;
    }
}
