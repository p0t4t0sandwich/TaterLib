/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.world.level;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;

import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Predicate;

public interface Level {
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
    @NonNull Identifier dimension();

    /**
     * Get a block from a position in the level. Returns minecraft:air if the position is empty.
     *
     * @param pos The block's position
     * @return The block at the position
     */
    @NonNull Block block(final @NonNull BlockPos pos);

    /**
     * Get a block from a position in the level. Returns minecraft:air if the position is empty.
     *
     * @param location The block's location
     * @return The block at the location
     */
    default @NonNull Block block(final @NonNull Location location) {
        return this.block(location.pos());
    }

    /**
     * Get the entities in the world.
     *
     * @param entity The entity.
     * @param pos1 The first position.
     * @param pos2 The second position.
     * @param predicate The predicate.
     */
    List<Entity> entities(
            final @NonNull Entity entity,
            final @NonNull Location pos1,
            final @NonNull Location pos2,
            final @NonNull Predicate<Entity> predicate);

    /**
     * Get the entities in the world.
     *
     * @param entity The entity.
     * @param pos1 The first position.
     * @param pos2 The second position.
     */
    default List<Entity> entities(
            final @NonNull Entity entity,
            final @NonNull Location pos1,
            final @NonNull Location pos2) {
        return entities(entity, pos1, pos2, _ -> true);
    }

    /**
     * Get the entities in the world.
     *
     * @param entity The entity.
     * @param radius The radius.
     * @param predicate The predicate.
     * @return The entities in the world that match the parameters.
     */
    List<Entity> entities(
            final @NonNull Entity entity,
            final double radius,
            final @NonNull Predicate<Entity> predicate);

    /**
     * Get the entities in the world.
     *
     * @param entity The entity.
     * @param radius The radius.
     * @return The entities in the world that match the parameters.
     */
    default List<Entity> entities(final @NonNull Entity entity, final double radius) {
        return entities(entity, radius, _ -> true);
    }
}
