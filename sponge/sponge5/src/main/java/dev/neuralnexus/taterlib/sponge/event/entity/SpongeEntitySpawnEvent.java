package dev.neuralnexus.taterlib.sponge.event.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.sponge.util.SpongeLocation;

import org.spongepowered.api.event.entity.SpawnEntityEvent;

/** Sponge implementation of {@link EntitySpawnEvent}. */
public class SpongeEntitySpawnEvent implements EntitySpawnEvent {
    private final SpawnEntityEvent.Custom event;

    public SpongeEntitySpawnEvent(SpawnEntityEvent.Custom event) {
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
    public Entity getEntity() {
        return new SpongeEntity(event.getEntities().get(0));
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new SpongeLocation(event.getEntities().get(0).getLocation());
    }
}
