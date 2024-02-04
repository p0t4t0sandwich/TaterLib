package dev.neuralnexus.taterlib.sponge.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterlib.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;

import org.spongepowered.api.entity.living.player.Player;
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
        return event.getCause().toString();
    }

    /** {@inheritDoc} */
    @Override
    public double getDamage() {
        return event.getFinalDamage();
    }

    /** {@inheritDoc} */
    @Override
    public Entity getEntity() {
        if (event.getTargetEntity() instanceof Player) {
            return new SpongePlayer((Player) event.getTargetEntity());
        } else {
            return new SpongeEntity(event.getTargetEntity());
        }
    }
}
