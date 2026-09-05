/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Key;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import org.jspecify.annotations.NonNull;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSources;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;

import java.util.Optional;

/** Sponge implementation of {@link LivingEntity}. */
public class SpongeLivingEntity extends SpongeEntity
        implements LivingEntity, Damageable, DataHolder {
    private final Living entity;

    /**
     * Constructor.
     *
     * @param entity The Sponge entity.
     */
    public SpongeLivingEntity(final @NonNull Living entity) {
        super(entity);
        this.entity = entity;
    }

    // ------------------------------------

    private final DataHolder data = DataHolder.create(this, Damageable.class);

    @Override
    public <E> Optional<Value<E>> value(final @NonNull Key<? extends Value<E>> key) {
        return this.data.value(key);
    }

    // ------------------------------------

    @Override
    public @NonNull Living unwrap() {
        return this.entity;
    }

    @Override
    public void damage(final double amount) {
        this.entity.damage(amount, DamageSources.GENERIC);
    }

    @Override
    public void damage(final double amount, final @NonNull Entity source) {
        this.entity.damage(
                amount,
                EntityDamageSource.builder()
                        .type(DamageTypes.ATTACK)
                        .entity(((SpongeLivingEntity) source).unwrap())
                        .build());
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
