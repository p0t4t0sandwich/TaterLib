/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.bukkit.entity;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Key;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.data.TaterDataHolder;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import org.jspecify.annotations.NonNull;

import java.lang.reflect.InvocationTargetException;
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
                Value.mutableOf(Keys.ABSORPTION, this::helper$absorption, this::helper$absorption);
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

    public double helper$absorption() {
        // Reflect to get ((CraftLivingEntity) entity).getHandle().getAbsorptionHearts();
        try {
            final Class<?> craftLivingEntity =
                    Class.forName("org.bukkit.craftbukkit.v1_13_R2.entity.CraftLivingEntity");
            final Object handle = craftLivingEntity.getMethod("getHandle").invoke(this.entity);
            return (double) handle.getClass().getMethod("getAbsorptionHearts").invoke(handle);
        } catch (final ClassNotFoundException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            TaterAPI.logger().error("Could not reflect to get entity's absorption", e);
            return 0;
        }
    }

    public void helper$absorption(final double amount) {
        // Reflect to set ((CraftLivingEntity) entity).getHandle().setAbsorptionHearts(amount);
        try {
            final Class<?> craftLivingEntity =
                    Class.forName("org.bukkit.craftbukkit.v1_13_R2.entity.CraftLivingEntity");
            final Object handle = craftLivingEntity.getMethod("getHandle").invoke(this.entity);
            handle.getClass().getMethod("setAbsorptionHearts", double.class).invoke(handle, amount);
        } catch (final ClassNotFoundException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            TaterAPI.logger().error("Could not reflect to set entity's absorption", e);
        }
    }
}
