package dev.neuralnexus.taterlib.forge.event.api.entity;

import dev.neuralnexus.taterlib.common.event.entity.EntityDamageEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

/**
 * Forge implementation of {@link EntityDamageEvent}.
 */
public class ForgeEntityDamageEvent extends ForgeEntityEvent implements EntityDamageEvent {
    private final LivingDamageEvent event;

    public ForgeEntityDamageEvent(LivingDamageEvent event) {
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
