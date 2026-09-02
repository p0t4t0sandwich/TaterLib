/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.forge.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Key;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.data.TaterDataHolder;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;

import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.Set;

/** Forge implementation of {@link LivingEntity}. */
public class ForgeLivingEntity extends ForgeEntity implements LivingEntity, Damageable, DataHolder {
    private final EntityLivingBase entity;

    /**
     * Constructor.
     *
     * @param entity The entity.
     */
    public ForgeLivingEntity(final @NonNull EntityLivingBase entity) {
        super(entity);
        this.entity = entity;

        final Value<Double> absorption =
                Value.mutableOf(
                        Keys.ABSORPTION,
                        () -> (double) this.entity.getAbsorptionAmount(),
                        (v) -> this.entity.setAbsorptionAmount(v.floatValue()));
        final Value<Double> health =
                Value.mutableOf(
                        Keys.HEALTH,
                        () -> (double) this.entity.getHealth(),
                        (v) -> this.entity.setHealth(v.floatValue()));
        final Value<Double> maxHealth =
                Value.mutableOf(
                        Keys.MAX_HEALTH,
                        () -> (double) this.entity.getMaxHealth(), // Potential loss in precision
                        (v) ->
                                this.entity
                                        .getEntityAttribute(SharedMonsterAttributes.maxHealth)
                                        .setAttribute(v));

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
    public @NonNull EntityLivingBase unwrap() {
        return this.entity;
    }

    @Override
    public void damage(final double amount) {
        this.entity.attackEntityFrom(DamageSource.generic, (float) amount);
    }

    @Override
    public void damage(final double amount, final @NonNull Entity source) {
        this.entity.attackEntityFrom(
                DamageSource.causeMobDamage(((ForgeLivingEntity) source).unwrap()), (float) amount);
    }
}
