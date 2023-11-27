package dev.neuralnexus.taterlib.sponge.event.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterlib.sponge.entity.SpongeEntity;

import org.spongepowered.api.event.entity.DamageEntityEvent;

/** Sponge implementation of {@link EntityDamageEvent}. */
public class SpongeEntityDamageEvent implements EntityDamageEvent {
    private final DamageEntityEvent event;

    public SpongeEntityDamageEvent(DamageEntityEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public String getCause() {
        return event.cause().toString();
    }

    /** {@inheritDoc} */
    @Override
    public double getDamage() {
        return event.finalDamage();
    }

    /** {@inheritDoc} */
    @Override
    public Entity getEntity() {
        return new SpongeEntity(event.entity());
    }
}
