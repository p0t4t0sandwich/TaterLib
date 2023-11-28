package dev.neuralnexus.taterlib.entity;

import dev.neuralnexus.taterlib.utils.Location;

import java.util.UUID;

/** The interface for an AbstractEntity */
public interface Entity {
    /**
     * Get the UUID of the entity
     *
     * @return The UUID of the entity
     */
    UUID getUniqueId();

    /**
     * Get the id of the entity
     *
     * @return The id of the entity
     */
    int getEntityId();

    /** Remove the entity */
    void remove();

    /**
     * Get the type of the entity
     *
     * @return The type of the entity
     */
    String getType();

    /**
     * Get the custom name of the entity
     *
     * @return The custom name of the entity
     */
    String getCustomName();

    /**
     * Set the custom name of the entity
     *
     * @param name The custom name of the entity
     */
    void setCustomName(String name);

    /**
     * Get the location of the entity
     *
     * @return The position of the entity
     */
    Location getLocation();

    /**
     * Get the x position of the entity
     *
     * @return The x position of the entity
     */
    double getX();

    /**
     * Get the y position of the entity
     *
     * @return The y position of the entity
     */
    double getY();

    /**
     * Get the z position of the entity
     *
     * @return The z position of the entity
     */
    double getZ();

    /**
     * Get the yaw of the entity
     *
     * @return The yaw of the entity
     */
    float getYaw();

    /**
     * Get the pitch of the entity
     *
     * @return The pitch of the entity
     */
    float getPitch();

    /**
     * Get the current dimension of the entity
     *
     * @return The current dimension of the entity
     */
    String getDimension();

    /**
     * Get the current biome of the entity
     *
     * @return The current biome of the entity
     */
    String getBiome();

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
    void teleport(Entity entity);
}
