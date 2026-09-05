/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Key;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import net.minecraft.entity.damage.DamageSource;

import org.jspecify.annotations.NonNull;

import java.util.Optional;

/** Vanilla implementation of {@link LivingEntity}. */
public class WrappedLivingEntity extends WrappedEntity
        implements LivingEntity, Damageable, DataHolder {
    private final net.minecraft.entity.living.LivingEntity entity;

    public WrappedLivingEntity(final net.minecraft.entity.living.@NonNull LivingEntity entity) {
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
    public net.minecraft.entity.living.@NonNull LivingEntity unwrap() {
        return this.entity;
    }

    @Override
    public void damage(final double amount) {
        this.entity.damage(DamageSource.GENERIC, (float) amount);
    }

    @Override
    public void damage(final double amount, final @NonNull Entity source) {
        this.entity.damage(
                DamageSource.mob(((WrappedLivingEntity) source).unwrap()), (float) amount);
    }
}
