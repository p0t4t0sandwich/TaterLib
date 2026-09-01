/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.Location;

import java.util.List;
import java.util.function.Predicate;

public interface WorldBridge {
    Identifier bridge$dimension();

    List<Entity> bridge$entities(Entity entity, double radius, Predicate<Entity> predicate);

    List<Entity> bridge$entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate);
}
