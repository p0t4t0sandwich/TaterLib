/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.sponge.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_8.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.v1_8.sponge.world.SpongeLocation;
import dev.neuralnexus.taterlib.world.Location;

import org.spongepowered.api.event.entity.SpawnEntityEvent;

/** Sponge implementation of {@link EntitySpawnEvent}. */
public class SpongeEntitySpawnEvent implements EntitySpawnEvent {
    private final SpawnEntityEvent.Custom event;

    public SpongeEntitySpawnEvent(SpawnEntityEvent.Custom event) {
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
    public Entity entity() {
        return new SpongeEntity(event.getEntities().get(0));
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new SpongeLocation(event.getEntities().get(0).getLocation());
    }
}
