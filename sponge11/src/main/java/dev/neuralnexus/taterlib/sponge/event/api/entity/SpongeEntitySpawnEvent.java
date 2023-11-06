package dev.neuralnexus.taterlib.sponge.event.api.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.sponge.util.SpongeLocation;
import org.spongepowered.api.event.entity.SpawnEntityEvent;

/**
 * Sponge implementation of {@link EntitySpawnEvent}.
 */
public class SpongeEntitySpawnEvent implements EntitySpawnEvent {
    private final SpawnEntityEvent.Pre event;

    public SpongeEntitySpawnEvent(SpawnEntityEvent.Pre event) {
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
    public Entity getEntity() {
        return new SpongeEntity(event.entities().get(0));
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getLocation() {
        return new SpongeLocation(event.entities().get(0).location());
    }
}
