/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16_3.forge.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_16_3.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.v1_16_3.forge.world.ForgeLocation;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraftforge.event.entity.living.LivingSpawnEvent;

/** Forge implementation of {@link EntitySpawnEvent}. */
public class ForgeEntitySpawnEvent extends ForgeEntityEvent implements EntitySpawnEvent {
    private final LivingSpawnEvent.SpecialSpawn event;

    public ForgeEntitySpawnEvent(LivingSpawnEvent.SpecialSpawn event) {
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
    public Location location() {
        return new ForgeLocation(((ForgeEntity) entity()).entity());
    }
}
