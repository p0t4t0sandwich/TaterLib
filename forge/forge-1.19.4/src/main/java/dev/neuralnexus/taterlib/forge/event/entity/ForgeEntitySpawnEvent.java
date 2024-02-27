package dev.neuralnexus.taterlib.forge.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.forge.world.ForgeLocation;
import dev.neuralnexus.taterlib.world.Location;

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
