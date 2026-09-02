/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_15_2.bukkit.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Key;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.data.TaterDataHolder;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.Set;

/** Bukkit implementation of {@link LivingEntity}. */
public class BukkitLivingEntity extends BukkitEntity
        implements LivingEntity, Damageable, DataHolder {
    private final org.bukkit.entity.LivingEntity entity;

    /**
     * Constructor.
     *
     * @param entity The Bukkit entity.
     */
    public BukkitLivingEntity(final org.bukkit.entity.@NonNull LivingEntity entity) {
        super(entity);
        this.entity = entity;

        final Value<Double> absorption =
                Value.mutableOf(
                        Keys.ABSORPTION,
                        this.entity::getAbsorptionAmount,
                        this.entity::setAbsorptionAmount);
        final Value<Double> health =
                Value.mutableOf(Keys.HEALTH, this.entity::getHealth, this.entity::setHealth);
        @SuppressWarnings("deprecation")
        final Value<Double> maxHealth =
                Value.mutableOf(
                        Keys.MAX_HEALTH, this.entity::getMaxHealth, this.entity::setMaxHealth);

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
    public org.bukkit.entity.@NonNull LivingEntity unwrap() {
        return this.entity;
    }

    @Override
    public void damage(final double amount) {
        this.entity.damage(amount);
    }

    @Override
    public void damage(final double amount, final @NonNull Entity source) {
        this.entity.damage(amount, ((BukkitEntity) source).unwrap());
    }
}
