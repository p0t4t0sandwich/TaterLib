package dev.neuralnexus.taterlib.sponge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.entity.LivingEntity;

import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSources;

/** Sponge implementation of {@link LivingEntity}. */
public class SpongeLivingEntity extends SpongeEntity implements LivingEntity {
    private final Living entity;

    /**
     * Constructor.
     *
     * @param entity The Sponge entity.
     */
    public SpongeLivingEntity(Living entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Gets the Sponge entity.
     *
     * @return The Sponge entity.
     */
    public Living getEntity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public void damage(double amount) {
        entity.damage(amount, DamageSources.GENERIC);
    }

    /** {@inheritDoc} */
    @Override
    public void damage(double amount, Entity source) {
        entity.damage(
                amount,
                DamageSource.builder()
                        .type(DamageTypes.MOB_ATTACK)
                        .entity(((SpongeLivingEntity) source).getEntity())
                        .build());
    }

    /** {@inheritDoc} */
    @Override
    public double getHealth() {
        return entity.health().get();
    }

    /** {@inheritDoc} */
    @Override
    public void setHealth(double health) {
        entity.health().set(health);
    }

    /** {@inheritDoc} */
    @Override
    public double getAbsorptionAmount() {
        return entity.absorption().get();
    }

    /** {@inheritDoc} */
    @Override
    public void setAbsorptionAmount(double amount) {
        entity.absorption().set(amount);
    }

    /** {@inheritDoc} */
    @Override
    public double getMaxHealth() {
        return entity.maxHealth().get();
    }

    /** {@inheritDoc} */
    @Override
    public void setMaxHealth(double health) {
        entity.maxHealth().set(health);
    }
}
