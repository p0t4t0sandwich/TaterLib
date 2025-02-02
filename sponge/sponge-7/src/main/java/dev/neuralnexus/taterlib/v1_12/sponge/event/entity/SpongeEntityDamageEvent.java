/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12.sponge.event.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterlib.v1_12.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.v1_12.sponge.entity.player.SpongePlayer;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.entity.DamageEntityEvent;

/** Sponge implementation of {@link EntityDamageEvent}. */
public class SpongeEntityDamageEvent implements EntityDamageEvent {
    private final DamageEntityEvent event;

    public SpongeEntityDamageEvent(DamageEntityEvent event) {
        this.event = event;
    }

    @Override
    public boolean cancelled() {
        return this.event.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    @Override
    public String cause() {
        return event.getCause().toString();
    }

    @Override
    public double damage() {
        return event.getFinalDamage();
    }

    @Override
    public Entity entity() {
        if (event.getTargetEntity() instanceof Player) {
            return new SpongePlayer((Player) event.getTargetEntity());
        } else {
            return new SpongeEntity(event.getTargetEntity());
        }
    }
}
