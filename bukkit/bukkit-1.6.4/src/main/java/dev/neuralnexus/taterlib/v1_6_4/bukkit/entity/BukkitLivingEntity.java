/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.bukkit.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.TaterLib;

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
            TaterLib.logger().error("Could not reflect to get entity's health", e);
            return 0;
        }
    }

    @Override
    public void setHealth(double health) {
        this.entity.setHealth(health);
    }

    @Override
    public double absorptionAmount() {
        // TODO: Check to see if this is possible in 1.6.4
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        // TODO: Check to see if this is possible in 1.6.4
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public double maxHealth() {
        // Reflect to get (double) entity.getMaxHealth();
        try {
            return (double) entity.getClass().getMethod("getMaxHealth").invoke(this.entity);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            TaterLib.logger().error("Could not reflect to get entity's max health", e);
            return 0;
        }
    }

    @Override
    public void setMaxHealth(double health) {
        this.entity.setMaxHealth(health);
    }
}
