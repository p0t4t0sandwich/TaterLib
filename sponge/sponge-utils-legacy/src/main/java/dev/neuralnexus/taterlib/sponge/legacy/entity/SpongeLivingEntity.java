/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.sponge.legacy.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

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
        return this.entity.health().get();
    }

    @Override
    public void setHealth(double health) {
        this.entity.health().set(health);
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public double absorptionAmount() {
        return entity.getHealthData().get(Keys.ABSORPTION).get();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        this.entity.getHealthData().set(Keys.ABSORPTION, amount);
    }

    @Override
    public double maxHealth() {
        return this.entity.maxHealth().get();
    }

    @Override
    public void setMaxHealth(double health) {
        this.entity.maxHealth().set(health);
    }
}
