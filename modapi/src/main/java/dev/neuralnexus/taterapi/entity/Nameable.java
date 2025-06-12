/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.meta.annotations.Range;
import dev.neuralnexus.taterapi.meta.annotations.VersionFeature;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;

import java.util.Optional;

/** Represents a nameable entity */
public interface Nameable {
    /**
     * Get the name of the entity
     *
     * @return The name of the entity
     */
    String name();

    /**
     * Get the display name of the entity
     *
     * @return The display name of the entity
     */
    default String displayName() {
        return this.name();
    }

    /**
     * Check if the entity has a custom name
     *
     * @return True if the entity has a custom name, false otherwise
     */
    @VersionFeature(name = "Nameable", compatible = @Range(min = MinecraftVersion.V6_1))
    default boolean hasCustomName() {
        return this.customName().isPresent();
    }

    /**
     * Get the custom name of the entity
     *
     * @return The custom name of the entity
     */
    @VersionFeature(name = "Nameable", compatible = @Range(min = MinecraftVersion.V6_1))
    default Optional<String> customName() {
        return Optional.empty();
    }

    /**
     * Set the custom name of the entity
     *
     * @param name The custom name of the entity
     */
    // TODO: Split into nametag interface?
    @VersionFeature(name = "Nameable", compatible = @Range(min = MinecraftVersion.V6_1))
    default void setCustomName(String name) {}
}
