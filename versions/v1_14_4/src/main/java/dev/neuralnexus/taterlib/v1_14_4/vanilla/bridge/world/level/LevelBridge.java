/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.level;

import dev.neuralnexus.taterapi.world.BlockPos;
import net.minecraft.resources.ResourceLocation;

import org.jspecify.annotations.NonNull;

public interface LevelBridge {
    @NonNull ResourceLocation bridge$dimension();

    @NonNull ResourceLocation bridge$biome(final @NonNull BlockPos pos);
}
