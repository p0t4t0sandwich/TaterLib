/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.meta.anno.Range;
import dev.neuralnexus.taterapi.meta.anno.VersionFeature;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;

/** Represents an entity that has health and can take damage. */
public interface Damageable extends DataHolder {
    /**
     * Gets the entity's absorption amount.
     *
     * @return The absorption amount of the entity
     */
    @VersionFeature(
            name = "Damageable#absorption()",
            incompatible = @Range(min = MinecraftVersion.B1_7_3, max = MinecraftVersion.V6_2))
    default double absorption() {
        return this.get(Keys.ABSORPTION).orElseThrow();
    }

    /**
     * Sets the entity's absorption amount.
     *
     * @param amount New absorption amount of the entity
     */
    @VersionFeature(
            name = "Damageable#absorption(double)",
            incompatible = @Range(min = MinecraftVersion.B1_7_3, max = MinecraftVersion.V6_2))
    default void absorption(double amount) {
        this.offer(Keys.ABSORPTION, amount);
    }

    /**
     * Gets the entity's current health.
     *
     * @return The current health of the entity
     */
    default double health() {
        return this.get(Keys.HEALTH).orElseThrow();
    }

    /**
     * Sets the entity's health.
     *
     * @param health New health of the entity
     */
    default void health(final double health) {
        this.offer(Keys.HEALTH, health);
    }

    /**
     * Gets the maximum health this entity has.
     *
     * @return The maximum health of this entity
     */
    @VersionFeature(name = "Damageable#maxHealth()", incompatible = @Range(MinecraftVersion.B1_7_3))
    default double maxHealth() {
        return this.get(Keys.MAX_HEALTH).orElseThrow();
    }

    /**
     * Sets the maximum health this entity has.
     *
     * @param health New maximum health of this entity
     */
    @VersionFeature(
            name = "Damageable#setMaxHealth(double)",
            incompatible = @Range(min = MinecraftVersion.B1_7_3, max = MinecraftVersion.V2_5))
    default void maxHealth(double health) {
        this.offer(Keys.MAX_HEALTH, health);
    }

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

    // TODO: Heal
}
