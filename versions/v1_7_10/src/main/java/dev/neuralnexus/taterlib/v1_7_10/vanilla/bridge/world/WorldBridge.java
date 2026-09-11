/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.Location;

import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Predicate;

public interface WorldBridge {
    @NonNull Identifier bridge$dimension();

    List<Entity> bridge$entities(
            final @NonNull Entity entity,
            final double radius,
            final @NonNull Predicate<Entity> predicate);

    List<Entity> bridge$entities(
            final @NonNull Entity entity,
            final @NonNull Location pos1,
            final @NonNull Location pos2,
            final @NonNull Predicate<Entity> predicate);
}
