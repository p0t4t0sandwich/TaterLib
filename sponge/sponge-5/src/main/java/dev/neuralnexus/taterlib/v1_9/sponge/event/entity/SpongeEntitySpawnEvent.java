package dev.neuralnexus.taterlib.v1_9.sponge.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_9.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.v1_9.sponge.world.SpongeLocation;
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
