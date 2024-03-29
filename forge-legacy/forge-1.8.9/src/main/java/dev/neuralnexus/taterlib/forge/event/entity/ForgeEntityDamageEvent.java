package dev.neuralnexus.taterlib.forge.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntityDamageEvent;

import net.minecraftforge.event.entity.living.LivingAttackEvent;

/** Forge implementation of {@link EntityDamageEvent}. */
public class ForgeEntityDamageEvent extends ForgeEntityEvent implements EntityDamageEvent {
    private final LivingAttackEvent event;

    public ForgeEntityDamageEvent(LivingAttackEvent event) {
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
        return event.source.damageType;
    }

    @Override
    public double damage() {
        return event.ammount;
    }
}
