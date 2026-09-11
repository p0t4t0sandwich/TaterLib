/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.forge.world;

import dev.neuralnexus.taterapi.world.level.ServerLevel;

import net.minecraft.world.WorldServer;

import org.jspecify.annotations.NonNull;

/** Forge implementation of {@link ServerLevel}. */
public class ForgeServerWorld extends ForgeWorld implements ServerLevel {
    private final @NonNull WorldServer level;

    public ForgeServerWorld(final @NonNull WorldServer level) {
        super(level);
        this.level = level;
    }

    @Override
    public @NonNull WorldServer unwrap() {
        return this.level;
    }
}
