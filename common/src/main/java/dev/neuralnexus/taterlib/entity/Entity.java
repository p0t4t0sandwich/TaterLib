package dev.neuralnexus.taterlib.entity;

import dev.neuralnexus.taterlib.utils.Location;

import java.util.UUID;

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
    String type();

    /**
     * Get the location of the entity
     *
     * @return The position of the entity
     */
    Location location();

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
    String dimension();

    /**
     * Get the current biome of the entity
     *
     * @return The current biome of the entity
     */
    String biome();

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
