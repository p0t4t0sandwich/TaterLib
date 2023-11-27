package dev.neuralnexus.taterlib.forge.event.entity;

import dev.neuralnexus.taterlib.common.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.forge.util.ForgeLocation;

import net.minecraftforge.event.entity.living.MobSpawnEvent;

/** Forge implementation of {@link EntitySpawnEvent}. */
public class ForgeEntitySpawnEvent extends ForgeEntityEvent implements EntitySpawnEvent {
    private final MobSpawnEvent.FinalizeSpawn event;

    public ForgeEntitySpawnEvent(MobSpawnEvent.FinalizeSpawn event) {
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
        return new ForgeLocation(((ForgeEntity) getEntity()).getEntity());
    }
}
