package dev.neuralnexus.taterlib.v1_19.forge.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntityDamageEvent;

import net.minecraftforge.event.entity.living.LivingDamageEvent;

/** Forge implementation of {@link EntityDamageEvent}. */
public class ForgeEntityDamageEvent extends ForgeEntityEvent implements EntityDamageEvent {
    private final LivingDamageEvent event;

    public ForgeEntityDamageEvent(LivingDamageEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return event.isCanceled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public String cause() {
        return event.getSource().toString();
    }

    @Override
    public double damage() {
        return event.getAmount();
    }
}
