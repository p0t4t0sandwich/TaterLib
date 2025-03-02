/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_9_4.fabric.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;

/** Vanilla implementation of {@link LivingEntity}. */
public class FabricLivingEntity extends FabricEntity implements LivingEntity {
    private final net.minecraft.entity.LivingEntity entity;

    /**
     * Constructor.
     *
     * @param entity The entity.
     */
    public FabricLivingEntity(net.minecraft.entity.LivingEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public net.minecraft.entity.LivingEntity unwrap() {
        return this.entity;
    }

    @Override
    public void damage(double amount) {
        this.entity.damage(DamageSource.GENERIC, (float) amount);
    }

    @Override
    public void damage(double amount, Entity source) {
        this.entity.damage(
                DamageSource.mob(((FabricLivingEntity) source).unwrap()), (float) amount);
    }

    @Override
    public double health() {
        return this.entity.getHealth();
    }

    @Override
    public void setHealth(double health) {
        this.entity.setHealth((float) health);
    }

    @Override
    public double absorptionAmount() {
        return this.entity.getAbsorption();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        this.entity.setAbsorption((float) amount);
    }

    @Override
    public double maxHealth() {
        return this.entity.getMaxHealth();
    }

    @Override
    public void setMaxHealth(double health) {
        this.entity.initializeAttribute(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(health);
    }
}
