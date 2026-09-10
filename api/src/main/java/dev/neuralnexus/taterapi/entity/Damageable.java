/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.meta.anno.Range;
import dev.neuralnexus.taterapi.meta.anno.VersionFeature;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;

import org.jspecify.annotations.NonNull;

/** Represents an entity that has health and can take damage. */
public interface Damageable extends DataHolder {
    /**
     * Get the entity's ABSORPTION attribute.
     *
     * @return The entity's ABSORPTION attribute
     */
    @VersionFeature(
            name = "Damageable#absorption()",
            incompatible = @Range(min = MinecraftVersion.B1_7_3, max = MinecraftVersion.V6_2))
    default @NonNull Value<Double> absorption() {
        return this.getOrThrow(Keys.ABSORPTION);
    }

    /**
     * Get the entity's HEALTH attribute.
     *
     * @return The entity's ABSORPTION attribute
     */
    default Value<Double> health() {
        return this.getOrThrow(Keys.HEALTH);
    }

    /**
     * Get the entity's MAX_HEALTH attribute.
     *
     * @return The entity's MAX_HEALTH attribute
     */
    @VersionFeature(name = "Damageable#maxHealth()", incompatible = @Range(MinecraftVersion.B1_7_3))
    default Value<Double> maxHealth() {
        return this.getOrThrow(Keys.MAX_HEALTH);
    }

    /**
     * Deals the given amount of damage to this entity.
     *
     * @param amount Amount of damage to deal
     */
    default void damage(final double amount) {
        this.action1(Keys.DAMAGE_GENERIC).unwrap().apply(amount);
    }

    // TODO: Consider cross-plat implications in wrapped contexts
    // Solution: instanceof switch to unwrap
    /**
     * Deals the given amount of damage to this entity.
     *
     * @param amount Amount of damage to deal
     * @param source Entity that caused the damage
     */
    default void damage(final double amount, final @NonNull Entity source) {
        this.action2(Keys.DAMAGE_ENTITY).unwrap().apply(amount, source);
    }

    // TODO: Heal
}
