package dev.neuralnexus.taterlib.v1_19.vanilla.event.entity;

import dev.neuralnexus.taterlib.event.Cancellable;
import dev.neuralnexus.taterlib.event.entity.EntityDamageEvent;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

/** Vanilla implementation of {@link EntityDamageEvent}. */
public class VanillaEntityDamageEvent extends VanillaEntityEvent implements EntityDamageEvent {
    private final DamageSource damageSource;
    private final float damage;
    private final Cancellable cancel;

    public VanillaEntityDamageEvent(
            Entity entity, DamageSource damageSource, float damage, Cancellable cancel) {
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
    public DamageSource getSource() {
        return damageSource;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return cancel.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        cancel.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public String getCause() {
        return damageSource.getMsgId();
    }

    /** {@inheritDoc} */
    @Override
    public double getDamage() {
        return damage;
    }
}
