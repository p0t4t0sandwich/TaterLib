package dev.neuralnexus.taterlib.bukkit.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.entity.LivingEntity;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSuportedException;

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
        entity.damage((int) amount);
    }

    /** {@inheritDoc} */
    @Override
    public void damage(double amount, Entity source) {
        entity.damage((int) amount, ((BukkitEntity) source).getEntity());
    }

    /** {@inheritDoc} */
    @Override
    public double getHealth() {
        return entity.getHealth();
    }

    /** {@inheritDoc} */
    @Override
    public void setHealth(double health) {
        entity.setHealth((int) health);
    }

    /** {@inheritDoc} */
    @Override
    public double getAbsorptionAmount() {
        // TODO: Check if this is supported on 1.2.5
        throw new VersionFeatureNotSuportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setAbsorptionAmount(double amount) {
        // TODO: Check if this is supported on 1.2.5
        throw new VersionFeatureNotSuportedException();
    }

    /** {@inheritDoc} */
    @Override
    public double getMaxHealth() {
        return entity.getMaxHealth();
    }

    /** {@inheritDoc} */
    @Override
    public void setMaxHealth(double health) {
        // TODO: Check if this is supported on 1.2.5
        throw new VersionFeatureNotSuportedException();
    }
}
