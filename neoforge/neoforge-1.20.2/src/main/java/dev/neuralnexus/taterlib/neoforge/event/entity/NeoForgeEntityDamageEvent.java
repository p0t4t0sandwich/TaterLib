package dev.neuralnexus.taterlib.neoforge.event.entity;

import dev.neuralnexus.taterlib.common.event.entity.EntityDamageEvent;

import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

/** NeoForge implementation of {@link EntityDamageEvent}. */
public class NeoForgeEntityDamageEvent extends NeoForgeEntityEvent implements EntityDamageEvent {
    private final LivingDamageEvent event;

    public NeoForgeEntityDamageEvent(LivingDamageEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return event.isCanceled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public String getCause() {
        return event.getSource().toString();
    }

    @Override
    public double getDamage() {
        return event.getAmount();
    }
}
