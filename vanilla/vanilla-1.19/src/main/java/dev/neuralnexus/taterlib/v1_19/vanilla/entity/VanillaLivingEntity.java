package dev.neuralnexus.taterlib.v1_19.vanilla.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.entity.LivingEntity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attributes;

/** Vanilla implementation of {@link LivingEntity}. */
public class VanillaLivingEntity extends VanillaEntity implements LivingEntity {
    private final net.minecraft.world.entity.LivingEntity entity;

    /**
     * Constructor.
     *
     * @param entity The entity.
     */
    public VanillaLivingEntity(net.minecraft.world.entity.LivingEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Gets the entity.
     *
     * @return The entity.
     */
    public net.minecraft.world.entity.LivingEntity getEntity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public void damage(double amount) {
        entity.hurt(DamageSource.GENERIC, (float) amount);
    }

    /** {@inheritDoc} */
    @Override
    public void damage(double amount, Entity source) {
        entity.hurt(
                DamageSource.mobAttack(((VanillaLivingEntity) source).getEntity()), (float) amount);
    }

    /** {@inheritDoc} */
    @Override
    public double getHealth() {
        return entity.getHealth();
    }

    /** {@inheritDoc} */
    @Override
    public void setHealth(double health) {
        entity.setHealth((float) health);
    }

    /** {@inheritDoc} */
    @Override
    public double getAbsorptionAmount() {
        return entity.getAbsorptionAmount();
    }

    /** {@inheritDoc} */
    @Override
    public void setAbsorptionAmount(double amount) {
        entity.setAbsorptionAmount((float) amount);
    }

    /** {@inheritDoc} */
    @Override
    public double getMaxHealth() {
        return entity.getMaxHealth();
    }

    /** {@inheritDoc} */
    @Override
    public void setMaxHealth(double health) {
        entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
    }
}
