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
        return event.source.damageType;
    }

    @Override
    public double getDamage() {
        return event.ammount;
    }
}
