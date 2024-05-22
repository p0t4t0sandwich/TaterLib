package dev.neuralnexus.taterlib.v1_17.fabric.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.entity.LivingEntity;

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

    /** {@inheritDoc} */
    @Override
    public void damage(double amount) {
        entity.damage(DamageSource.GENERIC, (float) amount);
    }

    /** {@inheritDoc} */
    @Override
    public void damage(double amount, Entity source) {
        entity.damage(DamageSource.mob(((FabricLivingEntity) source).entity()), (float) amount);
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
        entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(health);
    }
}
