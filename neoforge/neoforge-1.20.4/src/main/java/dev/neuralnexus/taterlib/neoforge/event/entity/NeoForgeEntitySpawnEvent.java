package dev.neuralnexus.taterlib.neoforge.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.neoforge.entity.NeoForgeEntity;
import dev.neuralnexus.taterlib.neoforge.util.NeoForgeLocation;

import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

/** NeoForge implementation of {@link EntitySpawnEvent}. */
public class NeoForgeEntitySpawnEvent extends NeoForgeEntityEvent implements EntitySpawnEvent {
    private final MobSpawnEvent.FinalizeSpawn event;

    public NeoForgeEntitySpawnEvent(MobSpawnEvent.FinalizeSpawn event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return event.isSpawnCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setSpawnCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new NeoForgeLocation(((NeoForgeEntity) getEntity()).getEntity());
    }
}
