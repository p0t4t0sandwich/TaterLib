/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;
import dev.neuralnexus.taterlib.v1_8_9.sponge.SpongeFactories;

import org.spongepowered.api.data.key.Keys;
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

    @Override
    public Living unwrap() {
        return this.entity;
    }

    @Override
    public void damage(double amount) {
        this.entity.damage(amount, DamageSources.GENERIC);
    }

    @Override
    public void damage(double amount, Entity source) {
        this.entity.damage(
                amount,
                EntityDamageSource.builder()
                        .type(DamageTypes.ATTACK)
                        .entity(((SpongeLivingEntity) source).unwrap())
                        .build());
    }

    @Override
    public double health() {
        return this.entity
                .getHealthData()
                .get(Keys.HEALTH)
                .orElseThrow(() -> new IllegalStateException("HEALTH key is not present"));
    }

    @Override
    public void setHealth(double health) {
        this.entity.getHealthData().set(Keys.HEALTH, health);
    }

    @Override
    public double absorptionAmount() {
        return SpongeFactories.absorptionAmount.get(this.entity);
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        SpongeFactories.setAbsorptionAmount.set(this.entity, amount);
    }

    @Override
    public double maxHealth() {
        return this.entity
                .getHealthData()
                .get(Keys.MAX_HEALTH)
                .orElseThrow(() -> new IllegalStateException("MAX_HEALTH key is not present"));
    }

    @Override
    public void setMaxHealth(double health) {
        this.entity.getHealthData().set(Keys.MAX_HEALTH, health);
    }
}
