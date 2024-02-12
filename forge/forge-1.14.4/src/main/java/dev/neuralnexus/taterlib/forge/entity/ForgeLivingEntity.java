package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.entity.LivingEntity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;

/** Vanilla implementation of {@link LivingEntity}. */
public class ForgeLivingEntity extends ForgeEntity implements LivingEntity {
    private final net.minecraft.entity.LivingEntity entity;

    /**
     * Constructor.
     *
     * @param entity The entity.
     */
    public ForgeLivingEntity(net.minecraft.entity.LivingEntity entity) {
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

    /** {@inheritDoc} */
    @Override
    public void damage(double amount) {
        entity.attackEntityFrom(DamageSource.GENERIC, (float) amount);
    }

    /** {@inheritDoc} */
    @Override
    public void damage(double amount, Entity source) {
        entity.attackEntityFrom(
                DamageSource.causeMobDamage(((ForgeLivingEntity) source).entity()), (float) amount);
    }

    /** {@inheritDoc} */
    @Override
    public double health() {
        return entity.getHealth();
    }

    /** {@inheritDoc} */
    @Override
    public void setHealth(double health) {
        entity.setHealth((float) health);
    }

    /** {@inheritDoc} */
    @Override
    public double absorptionAmount() {
        return entity.getAbsorptionAmount();
    }

    /** {@inheritDoc} */
    @Override
    public void setAbsorptionAmount(double amount) {
        entity.setAbsorptionAmount((float) amount);
    }

    /** {@inheritDoc} */
    @Override
    public double maxHealth() {
        return entity.getMaxHealth();
    }

    /** {@inheritDoc} */
    @Override
    public void setMaxHealth(double health) {
        entity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
    }
}
