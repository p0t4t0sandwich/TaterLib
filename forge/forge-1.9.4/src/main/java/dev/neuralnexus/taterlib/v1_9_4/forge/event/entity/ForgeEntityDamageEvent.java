/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_9_4.forge.event.entity;

import dev.neuralnexus.taterapi.event.entity.EntityDamageEvent;

import net.minecraftforge.event.entity.living.LivingAttackEvent;

/** Forge implementation of {@link EntityDamageEvent}. */
public class ForgeEntityDamageEvent extends ForgeEntityEvent implements EntityDamageEvent {
    private final LivingAttackEvent event;

    public ForgeEntityDamageEvent(LivingAttackEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public boolean cancelled() {
        return event.isCanceled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    @Override
    public String cause() {
        return event.getSource().toString();
    }

    @Override
    public double damage() {
        return event.getAmount();
    }
}
