/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_15_2.bukkit.entity;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import java.lang.reflect.InvocationTargetException;

/** Bukkit implementation of {@link LivingEntity}. */
public class BukkitLivingEntity extends BukkitEntity implements LivingEntity {
    private final org.bukkit.entity.LivingEntity entity;

    /**
     * Constructor.
     *
     * @param entity The Bukkit entity.
     */
    public BukkitLivingEntity(org.bukkit.entity.LivingEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public org.bukkit.entity.LivingEntity unwrap() {
        return this.entity;
    }

    @Override
    public void damage(double amount) {
        this.entity.damage(amount);
    }

    @Override
    public void damage(double amount, Entity source) {
        this.entity.damage(amount, ((BukkitEntity) source).unwrap());
    }

    @Override
    public double health() {
        // Reflect to get (double) entity.getHealth();
        try {
            return (double) entity.getClass().getMethod("getHealth").invoke(this.entity);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            TaterAPI.logger().error("Could not reflect to get entity's health", e);
            return 0;
        }
    }

    @Override
    public void setHealth(double health) {
        this.entity.setHealth(health);
    }

    @Override
    public double absorptionAmount() {
        // Reflect to get ((CraftLivingEntity) entity).getHandle().getAbsorptionHearts();
        try {
            Class<?> craftLivingEntity =
                    Class.forName("org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity");
            Object handle = craftLivingEntity.getMethod("getHandle").invoke(this.entity);
            return (double) handle.getClass().getMethod("getAbsorptionHearts").invoke(handle);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            TaterAPI.logger().error("Could not reflect to get entity's absorption", e);
            return 0;
        }
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        // Reflect to set ((CraftLivingEntity) entity).getHandle().setAbsorptionHearts(amount);
        try {
            Class<?> craftLivingEntity =
                    Class.forName("org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity");
            Object handle = craftLivingEntity.getMethod("getHandle").invoke(this.entity);
            handle.getClass().getMethod("setAbsorptionHearts", double.class).invoke(handle, amount);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            TaterAPI.logger().error("Could not reflect to get entity's absorption", e);
        }
    }

    @Override
    public double maxHealth() {
        // Reflect to get (double) entity.getMaxHealth();
        try {
            return (double) entity.getClass().getMethod("getMaxHealth").invoke(this.entity);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            TaterAPI.logger().error("Could not reflect to get entity's absorption", e);
            return 0;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setMaxHealth(double health) {
        this.entity.setMaxHealth(health);
    }
}
