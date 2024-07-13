/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import java.util.List;
import java.util.function.Predicate;

/** An interface to represent a world abstraction. */
public interface World {
    /**
     * Get the players in the world.
     *
     * @return The players in the world.
     */
    List<Player> players();

    /**
     * Get the dimension of the world.
     *
     * @return The dimension of the world.
     */
    ResourceKey dimension();

    /**
     * Get the entities in the world.
     *
     * @param entity The entity.
     * @param pos1 The first position.
     * @param pos2 The second position.
     * @param predicate The predicate.
     */
    List<Entity> entities(Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate);

    /**
     * Get the entities in the world.
     *
     * @param entity The entity.
     * @param pos1 The first position.
     * @param pos2 The second position.
     */
    default List<Entity> entities(Entity entity, Location pos1, Location pos2) {
        return entities(entity, pos1, pos2, e -> true);
    }

    /**
     * Get the entities in the world.
     *
     * @param entity The entity.
     * @param radius The radius.
     * @param predicate The predicate.
     * @return The entities in the world that match the parameters.
     */
    List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate);

    /**
     * Get the entities in the world.
     *
     * @param entity The entity.
     * @param radius The radius.
     * @return The entities in the world that match the parameters.
     */
    default List<Entity> entities(Entity entity, double radius) {
        return entities(entity, radius, e -> true);
    }
}
