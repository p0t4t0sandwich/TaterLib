/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.modapi.metadata.annotations.Range;
import dev.neuralnexus.modapi.metadata.annotations.VersionFeature;
import dev.neuralnexus.modapi.metadata.enums.MinecraftVersion;

import java.util.Optional;

/** Represents a nameable entity */
@VersionFeature(name = "Nameable", compatible = @Range(min = MinecraftVersion.V6_1))
public interface Nameable {
    /**
     * Get the custom name of the entity
     *
     * @return The custom name of the entity
     */
    Optional<String> customName();

    /**
     * Set the custom name of the entity
     *
     * @param name The custom name of the entity
     */
    void setCustomName(String name);
}
