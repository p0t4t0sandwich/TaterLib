package dev.neuralnexus.taterlib.entity;

/** Represents a nameable entity */
public interface Nameable {
    /**
     * Get the custom name of the entity
     *
     * @return The custom name of the entity
     */
    String customName();

    /**
     * Set the custom name of the entity
     *
     * @param name The custom name of the entity
     */
    void setCustomName(String name);
}
