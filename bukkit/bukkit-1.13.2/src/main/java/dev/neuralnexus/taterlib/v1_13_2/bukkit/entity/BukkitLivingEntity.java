/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.bukkit.entity;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Key;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import org.jspecify.annotations.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

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
    }

    // ------------------------------------

    private final DataHolder data = DataHolder.create(this, Damageable.class);

    @Override
    public <E> Optional<Value<E>> value(final @NonNull Key<? extends Value<E>> key) {
        return this.data.value(key);
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

    // TODO: Use MethodHandles
    public static double helper$absorption(final org.bukkit.entity.@NonNull LivingEntity entity) {
        // Reflect to get ((CraftLivingEntity) entity).getHandle().getAbsorptionHearts();
        try {
            final Class<?> craftLivingEntity =
                    Class.forName("org.bukkit.craftbukkit.v1_13_R2.entity.CraftLivingEntity");
            final Object handle = craftLivingEntity.getMethod("getHandle").invoke(entity);
            return (double) handle.getClass().getMethod("getAbsorptionHearts").invoke(handle);
        } catch (final ClassNotFoundException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            TaterAPI.logger().error("Could not reflect to get entity's absorption", e);
            return 0;
        }
    }

    // TODO: Use MethodHandles
    public static void helper$absorption(
            final org.bukkit.entity.@NonNull LivingEntity entity, final double amount) {
        // Reflect to set ((CraftLivingEntity) entity).getHandle().setAbsorptionHearts(amount);
        try {
            final Class<?> craftLivingEntity =
                    Class.forName("org.bukkit.craftbukkit.v1_13_R2.entity.CraftLivingEntity");
            final Object handle = craftLivingEntity.getMethod("getHandle").invoke(entity);
            handle.getClass().getMethod("setAbsorptionHearts", double.class).invoke(handle, amount);
        } catch (final ClassNotFoundException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            TaterAPI.logger().error("Could not reflect to set entity's absorption", e);
        }
    }
}
