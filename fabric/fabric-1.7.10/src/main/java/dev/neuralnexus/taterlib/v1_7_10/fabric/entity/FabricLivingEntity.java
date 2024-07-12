/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.fabric.entity;

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

    /**
     * Gets the entity.
     *
     * @return The entity.
     */
    public net.minecraft.entity.LivingEntity entity() {
        return entity;
    }

    @Override
    public void damage(double amount) {
        entity.damage(DamageSource.GENERIC, (float) amount);
    }

    @Override
    public void damage(double amount, Entity source) {
        entity.damage(DamageSource.mob(((FabricLivingEntity) source).entity()), (float) amount);
    }

    @Override
    public double health() {
        return entity.getHealth();
    }

    @Override
    public void setHealth(double health) {
        entity.setHealth((float) health);
    }

    @Override
    public double absorptionAmount() {
        return entity.getAbsorption();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        entity.setAbsorption((float) amount);
    }

    @Override
    public double maxHealth() {
        return entity.getMaxHealth();
    }

    @Override
    public void setMaxHealth(double health) {
        entity.initializeAttribute(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(health);
    }
}
