package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.entity.LivingEntity;

import net.minecraft.world.entity.ai.attributes.Attributes;

/** Forge implementation of {@link LivingEntity}. */
public class ForgeLivingEntity extends ForgeEntity implements LivingEntity {
    private final net.minecraft.world.entity.LivingEntity entity;

    /**
     * Constructor.
     *
     * @param entity The entity.
     */
    public ForgeLivingEntity(net.minecraft.world.entity.LivingEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Gets the entity.
     *
     * @return The entity.
     */
    public net.minecraft.world.entity.LivingEntity entity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public void damage(double amount) {
        entity.hurt(entity.getLevel().damageSources().generic(), (float) amount);
    }

    /** {@inheritDoc} */
    @Override
    public void damage(double amount, Entity source) {
        entity.hurt(entity.getLevel().damageSources().mobAttack(((ForgeLivingEntity) source).entity()), (float) amount);
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
        entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
    }
}
