/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.MissingKeyException;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.Location;

import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Predicate;

/** The interface for an AbstractEntity */
public interface Entity extends Actor, DataHolder, Identifiable, Locatable, Nameable {
    /**
     * Get the name of the entity
     *
     * @return The name of the entity
     */
    default @NonNull String name() {
        try {
            if (this.hasCustomName()) {
                //noinspection DataFlowIssue
                return this.customName().unwrap().get();
            }
            return this.displayName();
        } catch (final VersionFeatureNotSupportedException | MissingKeyException ignored) {
        }
        return this.type().asString();
    }

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
    Identifier type();

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
        return this.nearbyEntities(radius, e -> true);
    }

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
