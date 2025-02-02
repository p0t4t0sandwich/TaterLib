/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.modapi.metadata.annotations.Range;
import dev.neuralnexus.modapi.metadata.annotations.VersionFeature;
import dev.neuralnexus.modapi.metadata.enums.MinecraftVersion;

/** Represents an entity that has health and can take damage. */
public interface Damageable extends Entity {
    /**
     * Deals the given amount of damage to this entity.
     *
     * @param amount Amount of damage to deal
     */
    void damage(double amount);

    /**
     * Deals the given amount of damage to this entity.
     *
     * @param amount Amount of damage to deal
     * @param source Entity that caused the damage
     */
    void damage(double amount, Entity source);

    /**
     * Gets the entity's current health.
     *
     * @return The current health of the entity
     */
    double health();

    /**
     * Sets the entity's health.
     *
     * @param health New health of the entity
     */
    void setHealth(double health);

    /**
     * Gets the entity's absorption amount.
     *
     * @return The absorption amount of the entity
     */
    @VersionFeature(name = "Damageable#absorptionAmount()", incompatible = @Range(MinecraftVersion.B1_7_3))
    double absorptionAmount();

    /**
     * Sets the entity's absorption amount.
     *
     * @param amount New absorption amount of the entity
     */
    @VersionFeature(name = "Damageable#setAbsorptionAmount(double)", incompatible = @Range(MinecraftVersion.B1_7_3))
    void setAbsorptionAmount(double amount);

    /**
     * Gets the maximum health this entity has.
     *
     * @return The maximum health of this entity
     */
    @VersionFeature(name = "Damageable#absorptionAmount()", incompatible = @Range(MinecraftVersion.B1_7_3))
    double maxHealth();

    /**
     * Sets the maximum health this entity has.
     *
     * @param health New maximum health of this entity
     */
    @VersionFeature(name = "Damageable#setMaxHealth(double)", incompatible = @Range(MinecraftVersion.B1_7_3))
    void setMaxHealth(double health);
}
