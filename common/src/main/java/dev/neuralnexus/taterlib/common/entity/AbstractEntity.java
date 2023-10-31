package dev.neuralnexus.taterlib.common.entity;

import java.util.UUID;

/**
 * The interface for an AbstractEntity
 */
public interface AbstractEntity {
    /**
     * Get the UUID of the entity
     * @return The UUID of the entity
     */
    UUID getUniqueId();

    /**
     * Get the id of the entity
     * @return The id of the entity
     */
    int getEntityId();

    /**
     * Remove the entity
     */
    void remove();

    /**
     * Get the type of the entity
     * @return The type of the entity
     */
    String getType();

    /**
     * Get the custom name of the entity
     * @return The custom name of the entity
     */
    String getCustomName();

    /**
     * Set the custom name of the entity
     * @param name The custom name of the entity
     */
    void setCustomName(String name);

    /**
     * Get the x position of the entity
     * @return The x position of the entity
     */
    double getX();

    /**
     * Get the y position of the entity
     * @return The y position of the entity
     */
    double getY();

    /**
     * Get the z position of the entity
     * @return The z position of the entity
     */
    double getZ();

    /**
     * Get the current dimension of the entity
     * @return The current dimension of the entity
     */
    String getDimension();

    /**
     * Get the current biome of the entity
     * @return The current biome of the entity
     */
    String getBiome();
}
