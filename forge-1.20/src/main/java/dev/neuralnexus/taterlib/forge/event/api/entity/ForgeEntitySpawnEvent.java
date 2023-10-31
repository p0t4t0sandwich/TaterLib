package dev.neuralnexus.taterlib.forge.event.api.entity;

import dev.neuralnexus.taterlib.common.event.entity.AbstractEntitySpawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import net.minecraftforge.event.entity.living.MobSpawnEvent;

/**
 * Forge implementation of {@link AbstractEntitySpawnEvent}.
 */
public class ForgeEntitySpawnEvent extends ForgeEntityEvent implements AbstractEntitySpawnEvent {
    private final MobSpawnEvent.FinalizeSpawn event;

    public ForgeEntitySpawnEvent(MobSpawnEvent.FinalizeSpawn event) {
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
