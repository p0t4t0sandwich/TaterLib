package dev.neuralnexus.taterlib.neoforge.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntitySpawnEvent;
import dev.neuralnexus.taterlib.common.abstractions.utils.Location;
import net.minecraftforge.event.entity.living.MobSpawnEvent;

/**
 * Forge implementation of {@link AbstractEntitySpawnEvent}.
 */
public class NeoForgeEntitySpawnEvent extends NeoForgeEntityEvent implements AbstractEntitySpawnEvent {
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
