/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_11.sponge.entity;

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

    /**
     * Gets the Sponge entity.
     *
     * @return The Sponge entity.
     */
    public Living entity() {
        return entity;
    }

    @Override
    public void damage(double amount) {
        entity.damage(amount, DamageSources.GENERIC);
    }

    @Override
    public void damage(double amount, Entity source) {
        entity.damage(
                amount,
                EntityDamageSource.builder()
                        .type(DamageTypes.ATTACK)
                        .entity(((SpongeLivingEntity) source).entity())
                        .build());
    }

    @Override
    public double health() {
        return entity.health().get();
    }

    @Override
    public void setHealth(double health) {
        entity.health().set(health);
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public double absorptionAmount() {
        return entity.getHealthData().get(Keys.ABSORPTION).get();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        entity.getHealthData().set(Keys.ABSORPTION, amount);
    }

    @Override
    public double maxHealth() {
        return entity.maxHealth().get();
    }

    @Override
    public void setMaxHealth(double health) {
        entity.maxHealth().set(health);
    }
}
