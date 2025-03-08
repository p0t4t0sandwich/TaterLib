/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.event.entity;

import dev.neuralnexus.taterapi.event.Cancellable;
import dev.neuralnexus.taterapi.event.entity.EntityDamageEvent;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

/** Vanilla implementation of {@link EntityDamageEvent}. */
public class VanillaEntityDamageEvent extends VanillaEntityEvent implements EntityDamageEvent {
    private final DamageSource damageSource;
    private final float damage;
    private final Cancellable cancel;

    public VanillaEntityDamageEvent(
            Entity entity,
            DamageSource damageSource,
            float originalHealth,
            float damage,
            Cancellable cancel) {
        super(entity);
        this.damageSource = damageSource;
        this.damage = damage;
        this.cancel = cancel;
    }

    /**
     * Get the source of the damage
     *
     * @return The source of the damage
     */
    public DamageSource source() {
        return damageSource;
    }

    @Override
    public boolean cancelled() {
        return cancel.cancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        cancel.setCancelled(cancelled);
    }

    @Override
    public String cause() {
        return damageSource.getDamageType();
    }

    @Override
    public double damage() {
        return damage;
    }
}
