/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

/** The interface for an AbstractEntity */
public interface Entity extends Nameable {
    /**
     * Get the UUID of the entity
     *
     * @return The UUID of the entity
     */
    UUID uuid();

    /**
     * Get the id of the entity
     *
     * @return The id of the entity
     */
    int entityId();

    /** Remove the entity */
    void remove();

    /**
     * Get the type of the entity
     *
     * @return The type of the entity
     */
    ResourceKey type();

    /**
     * Get the location of the entity
     *
     * @return The position of the entity
     */
    Location location();

    /**
     * Get the world of the entity
     *
     * @return The world of the entity
     */
    default World world() {
        return location().world();
    }

    /**
     * Get nearby entities
     *
     * @param radius The radius
     * @param predicate The predicate
     * @return The entities in the world that match the parameters
     */
    default List<Entity> nearbyEntities(double radius, Predicate<Entity> predicate) {
        return location().world().entities(this, radius, predicate);
    }

    /**
     * Get nearby entities
     *
     * @param radius The radius
     * @return The entities in the world that match the parameters
     */
    default List<Entity> nearbyEntities(double radius) {
        return nearbyEntities(radius, e -> true);
    }

    /**
     * Get the x position of the entity
     *
     * @return The x position of the entity
     */
    default double x() {
        return location().x();
    }

    /**
     * Get the y position of the entity
     *
     * @return The y position of the entity
     */
    default double y() {
        return location().y();
    }

    /**
     * Get the z position of the entity
     *
     * @return The z position of the entity
     */
    default double z() {
        return location().z();
    }

    /**
     * Get the yaw of the entity
     *
     * @return The yaw of the entity
     */
    default float yaw() {
        return location().yaw();
    }

    /**
     * Get the pitch of the entity
     *
     * @return The pitch of the entity
     */
    default float pitch() {
        return location().pitch();
    }

    /**
     * Get the current dimension of the entity
     *
     * @return The current dimension of the entity
     */
    default ResourceKey dimension() {
        return world().dimension();
    }

    /**
     * Get the current biome of the entity
     *
     * @return The current biome of the entity
     */
    ResourceKey biome();

    /**
     * Teleport the entity to the given Location
     *
     * @param location The location to teleport the entity to
     */
    void teleport(Location location);

    /**
     * Teleport the entity to another entity
     *
     * @param entity The entity to teleport the entity to
     */
    default void teleport(Entity entity) {
        teleport(entity.location());
    }
}
