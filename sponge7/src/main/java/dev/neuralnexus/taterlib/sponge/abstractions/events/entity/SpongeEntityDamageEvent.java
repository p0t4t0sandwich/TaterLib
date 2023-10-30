package dev.neuralnexus.taterlib.sponge.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityDamageEvent;
import dev.neuralnexus.taterlib.sponge.abstractions.entity.SpongeEntity;
import org.spongepowered.api.event.entity.DamageEntityEvent;

/**
 * Sponge implementation of {@link AbstractEntityDamageEvent}.
 */
public class SpongeEntityDamageEvent implements AbstractEntityDamageEvent {
    private final DamageEntityEvent event;

    public SpongeEntityDamageEvent(DamageEntityEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCause() {
        return event.getCause().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getDamage() {
        return event.getFinalDamage();
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractEntity getEntity() {
        return new SpongeEntity(event.getTargetEntity());
    }
}
