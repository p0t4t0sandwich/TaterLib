package dev.neuralnexus.taterlib.sponge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.entity.LivingEntity;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;

import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSources;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;

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
    public Living entity() {
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
                EntityDamageSource.builder()
                        .type(DamageTypes.ATTACK)
                        .entity(((SpongeLivingEntity) source).entity())
                        .build());
    }

    /** {@inheritDoc} */
    @Override
    public double health() {
        return entity.health().get();
    }

    /** {@inheritDoc} */
    @Override
    public void setHealth(double health) {
        entity.health().set(health);
    }

    /** {@inheritDoc} */
    @Override
    public double absorptionAmount() {
        // TODO: Find absorption for Sponge 5
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setAbsorptionAmount(double amount) {
        // TODO: Find absorption for Sponge 5
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public double maxHealth() {
        return entity.maxHealth().get();
    }

    /** {@inheritDoc} */
    @Override
    public void setMaxHealth(double health) {
        entity.maxHealth().set(health);
    }
}
