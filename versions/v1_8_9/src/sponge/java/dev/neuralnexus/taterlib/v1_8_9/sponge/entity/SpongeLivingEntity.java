/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Key;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.data.TaterDataHolder;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;
import dev.neuralnexus.taterlib.v1_8_9.sponge.SpongeFactories;

import org.jspecify.annotations.NonNull;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSources;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;

import java.util.Optional;
import java.util.Set;

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

        final Value<Double> absorption =
                Value.mutableOf(Keys.ABSORPTION, this::helper$absorption, this::helper$absorption);
        final Value<Double> health =
                Value.mutableOf(Keys.HEALTH, this::helper$health, this::helper$health);
        final Value<Double> maxHealth =
                Value.mutableOf(Keys.MAX_HEALTH, this::helper$maxHealth, this::helper$maxHealth);

        this.data.register(absorption, health, maxHealth);
    }

    // ------------------------------------

    private final TaterDataHolder data = new TaterDataHolder();

    @Override
    public <E> Optional<E> offer(final @NonNull Key<? extends Value<E>> key, final E value) {
        return this.data.offer(key, value);
    }

    @Override
    public <E> Optional<E> get(final @NonNull Key<? extends Value<E>> key) {
        return this.data.get(key);
    }

    @Override
    public Set<Key<?>> getKeys() {
        return this.data.getKeys();
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

    public double helper$absorption() {
        return SpongeFactories.absorptionAmount.get(this.entity);
    }

    public void helper$absorption(double amount) {
        SpongeFactories.setAbsorptionAmount.set(this.entity, amount);
    }

    public double helper$health() {
        return this.entity
                .getHealthData()
                .get(org.spongepowered.api.data.key.Keys.HEALTH)
                .orElseThrow(() -> new IllegalStateException("HEALTH key is not present"));
    }

    public void helper$health(double health) {
        this.entity.getHealthData().set(org.spongepowered.api.data.key.Keys.HEALTH, health);
    }

    public double helper$maxHealth() {
        return this.entity
                .getHealthData()
                .get(org.spongepowered.api.data.key.Keys.MAX_HEALTH)
                .orElseThrow(() -> new IllegalStateException("MAX_HEALTH key is not present"));
    }

    public void helper$maxHealth(double health) {
        this.entity.getHealthData().set(org.spongepowered.api.data.key.Keys.MAX_HEALTH, health);
    }
}
