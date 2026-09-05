/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Key;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

import org.jspecify.annotations.NonNull;

import java.util.Optional;

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
    }

    // ------------------------------------

    private final DataHolder data = DataHolder.create(this, Damageable.class);

    @Override
    public <E> Optional<Value<E>> value(final @NonNull Key<? extends Value<E>> key) {
        return this.data.value(key);
    }

    // ------------------------------------

    @Override
    public @NonNull EntityLivingBase unwrap() {
        return this.entity;
    }

    @Override
    public void damage(final double amount) {
        this.entity.attackEntityFrom(DamageSource.GENERIC, (float) amount);
    }

    @Override
    public void damage(final double amount, final @NonNull Entity source) {
        this.entity.attackEntityFrom(
                DamageSource.causeMobDamage(((ForgeLivingEntity) source).unwrap()), (float) amount);
    }
}
