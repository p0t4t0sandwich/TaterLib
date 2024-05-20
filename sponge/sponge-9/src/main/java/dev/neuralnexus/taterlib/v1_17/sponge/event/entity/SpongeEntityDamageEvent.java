package dev.neuralnexus.taterlib.v1_17.sponge.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterlib.v1_17.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.v1_17.sponge.player.SpongePlayer;

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
    public boolean cancelled() {
        return event.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public String cause() {
        return event.cause().toString();
    }

    /** {@inheritDoc} */
    @Override
    public double damage() {
        return event.finalDamage();
    }

    /** {@inheritDoc} */
    @Override
    public Entity entity() {
        if (event.entity() instanceof Player) {
            return new SpongePlayer((Player) event.entity());
        } else {
            return new SpongeEntity(event.entity());
        }
    }
}
