/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.bukkit.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.entity.LivingEntity;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;

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

    /** {@inheritDoc} */
    @Override
    public void damage(double amount) {
        entity.damage(amount);
    }

    /** {@inheritDoc} */
    @Override
    public void damage(double amount, Entity source) {
        entity.damage(amount, ((BukkitEntity) source).entity());
    }

    /** {@inheritDoc} */
    @Override
    public double health() {
        // Reflect to get (double) entity.getHealth();
        try {
            return (double) entity.getClass().getMethod("getHealth").invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setHealth(double health) {
        entity.setHealth(health);
    }

    /** {@inheritDoc} */
    @Override
    public double absorptionAmount() {
        // TODO: Check to see if this is possible in 1.6.4
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setAbsorptionAmount(double amount) {
        // TODO: Check to see if this is possible in 1.6.4
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public double maxHealth() {
        // Reflect to get (double) entity.getMaxHealth();
        try {
            return (double) entity.getClass().getMethod("getMaxHealth").invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setMaxHealth(double health) {
        entity.setMaxHealth(health);
    }
}
