/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.bukkit.entity;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import org.jspecify.annotations.NonNull;

import java.lang.reflect.InvocationTargetException;

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
        // Reflect to get ((CraftLivingEntity) entity).getHandle().bn();
        try {
            final Class<?> craftLivingEntity =
                    Class.forName("org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity");
            final Object handle = craftLivingEntity.getMethod("getHandle").invoke(entity);
            return (double) (float) handle.getClass().getMethod("bn").invoke(handle);
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
        // Reflect to set ((CraftLivingEntity) entity).getHandle().m(amount);
        try {
            final Class<?> craftLivingEntity =
                    Class.forName("org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity");
            final Object handle = craftLivingEntity.getMethod("getHandle").invoke(entity);
            handle.getClass().getMethod("m", float.class).invoke(handle, (float) amount);
        } catch (final ClassNotFoundException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            TaterAPI.logger().error("Could not reflect to set entity's absorption", e);
        }
    }

    // TODO: Use MethodHandles
    public static double helper$health(final org.bukkit.entity.@NonNull LivingEntity entity) {
        // Reflect to get (double) entity.getHealth();
        try {
            return (double) entity.getClass().getMethod("getHealth").invoke(entity);
        } catch (final IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            TaterAPI.logger().error("Could not reflect to get entity's health", e);
            return 0;
        }
    }

    // TODO: Use MethodHandles
    public static double helper$maxHealth(final org.bukkit.entity.@NonNull LivingEntity entity) {
        // Reflect to get (double) entity.getMaxHealth();
        try {
            return (double) entity.getClass().getMethod("getMaxHealth").invoke(entity);
        } catch (final IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            TaterAPI.logger().error("Could not reflect to get entity's max health", e);
            return 0;
        }
    }
}
