/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_10_2.forge.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;

/** Vanilla implementation of {@link LivingEntity}. */
public class ForgeLivingEntity extends ForgeEntity implements LivingEntity {
    private final EntityLivingBase entity;

    /**
     * Constructor.
     *
     * @param entity The entity.
     */
    public ForgeLivingEntity(EntityLivingBase entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public EntityLivingBase unwrap() {
        return this.entity;
    }

    @Override
    public void damage(double amount) {
        this.entity.attackEntityFrom(DamageSource.generic, (float) amount);
    }

    @Override
    public void damage(double amount, Entity source) {
        this.entity.attackEntityFrom(
                DamageSource.causeMobDamage(((ForgeLivingEntity) source).unwrap()), (float) amount);
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
        return this.entity.getAbsorptionAmount();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        this.entity.setAbsorptionAmount((float) amount);
    }

    @Override
    public double maxHealth() {
        return this.entity.getMaxHealth();
    }

    @Override
    public void setMaxHealth(double health) {
        this.entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
    }
}
