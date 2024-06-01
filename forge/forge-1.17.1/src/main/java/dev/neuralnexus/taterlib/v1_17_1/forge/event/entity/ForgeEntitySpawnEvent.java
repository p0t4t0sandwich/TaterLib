package dev.neuralnexus.taterlib.v1_17_1.forge.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_17.vanilla.entity.VanillaEntity;
import dev.neuralnexus.taterlib.v1_17.vanilla.world.VanillaLocation;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraftforge.event.entity.living.LivingSpawnEvent;

/** Forge implementation of {@link EntitySpawnEvent}. */
public class ForgeEntitySpawnEvent extends ForgeEntityEvent implements EntitySpawnEvent {
    private final LivingSpawnEvent.SpecialSpawn event;

    public ForgeEntitySpawnEvent(LivingSpawnEvent.SpecialSpawn event) {
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
        return new VanillaLocation(((VanillaEntity) entity()).entity());
    }
}
