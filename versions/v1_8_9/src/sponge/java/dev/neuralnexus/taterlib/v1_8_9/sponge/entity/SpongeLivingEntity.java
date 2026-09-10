/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.LivingEntity;
import dev.neuralnexus.taterapi.entity.Nameable;

import org.jspecify.annotations.NonNull;
import org.spongepowered.api.entity.living.Living;

/** Sponge implementation of {@link LivingEntity}. */
public class SpongeLivingEntity extends SpongeEntity
        implements LivingEntity, Damageable, DataHolder, Identifiable, Nameable {
    private final @NonNull Living entity;

    /**
     * Constructor.
     *
     * @param entity The Sponge entity.
     */
    public SpongeLivingEntity(final @NonNull Living entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public @NonNull Living unwrap() {
        return this.entity;
    }

    public static double helper$health(final @NonNull Living entity) {
        return entity.getHealthData()
                .get(org.spongepowered.api.data.key.Keys.HEALTH)
                .orElseThrow(() -> new IllegalStateException("HEALTH key is not present"));
    }

    public static void helper$health(final @NonNull Living entity, final double health) {
        entity.getHealthData().set(org.spongepowered.api.data.key.Keys.HEALTH, health);
    }

    public static double helper$maxHealth(final @NonNull Living entity) {
        return entity.getHealthData()
                .get(org.spongepowered.api.data.key.Keys.MAX_HEALTH)
                .orElseThrow(() -> new IllegalStateException("MAX_HEALTH key is not present"));
    }

    public static void helper$maxHealth(final @NonNull Living entity, final double health) {
        entity.getHealthData().set(org.spongepowered.api.data.key.Keys.MAX_HEALTH, health);
    }
}
