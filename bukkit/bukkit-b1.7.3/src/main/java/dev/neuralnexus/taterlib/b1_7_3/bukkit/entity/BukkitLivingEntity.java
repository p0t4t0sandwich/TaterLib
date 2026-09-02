/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.b1_7_3.bukkit.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Key;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.data.TaterDataHolder;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;

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

        final Value<Double> health =
                Value.mutableOf(
                        Keys.HEALTH,
                        () -> (double) this.entity.getHealth(),
                        (v) -> this.entity.setHealth((int) v.doubleValue()));
        final Value<Double> maxHealth =
                Value.mutableOf(
                        Keys.MAX_HEALTH,
                        () -> (double) 200, // Value is hard-coded as 200??
                        (_) -> {
                            throw new VersionFeatureNotSupportedException();
                        });

        this.data.register(health, maxHealth);
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
        this.entity.damage((int) amount);
    }

    @Override
    public void damage(final double amount, final @NonNull Entity source) {
        this.entity.damage((int) amount, ((BukkitEntity) source).unwrap());
    }
}
