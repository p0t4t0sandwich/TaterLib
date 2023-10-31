package dev.neuralnexus.taterlib.neoforge.event.api.entity;

import dev.neuralnexus.taterlib.common.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

/**
 * NeoForge implementation of {@link EntitySpawnEvent}.
 */
public class NeoForgeEntitySpawnEvent extends NeoForgeEntityEvent implements EntitySpawnEvent {
    private final MobSpawnEvent.FinalizeSpawn event;

    public NeoForgeEntitySpawnEvent(MobSpawnEvent.FinalizeSpawn event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return event.isSpawnCancelled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setSpawnCancelled(cancelled);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getLocation() {
        return new Location(event.getX(), event.getY(), event.getZ(), getEntity().getDimension());
    }
}
