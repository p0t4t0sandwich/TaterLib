package dev.neuralnexus.taterlib.neoforge.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

/**
 * NeoForge implementation of {@link AbstractEntityDamageEvent}.
 */
public class NeoForgeEntityDamageEvent extends NeoForgeEntityEvent implements AbstractEntityDamageEvent {
    private final LivingDamageEvent event;

    public NeoForgeEntityDamageEvent(LivingDamageEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return event.isCanceled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCause() {
        return event.getSource().toString();
    }

    @Override
    public double getDamage() {
        return event.getAmount();
    }
}
