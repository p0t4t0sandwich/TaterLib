/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20.bukkit.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

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
        return this.entity.getHealth();
    }

    @Override
    public void setHealth(double health) {
        this.entity.setHealth(health);
    }

    @Override
    public double absorptionAmount() {
        return entity.getAbsorptionAmount();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        entity.setAbsorptionAmount(amount);
    }

    @Override
    @SuppressWarnings("deprecation")
    public double maxHealth() {
        return this.entity.getMaxHealth();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setMaxHealth(double health) {
        this.entity.setMaxHealth(health);
    }
}
