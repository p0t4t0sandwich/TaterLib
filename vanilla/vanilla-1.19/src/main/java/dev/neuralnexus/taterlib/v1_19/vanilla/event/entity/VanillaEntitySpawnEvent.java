package dev.neuralnexus.taterlib.v1_19.vanilla.event.entity;

import dev.neuralnexus.taterlib.event.Cancellable;
import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.v1_19.vanilla.util.VanillaLocation;

import net.minecraft.world.entity.Entity;

/** Vanilla implementation of {@link EntitySpawnEvent}. */
public class VanillaEntitySpawnEvent extends VanillaEntityEvent implements EntitySpawnEvent {
    private final Entity entity;
    private final Cancellable cancel;

    public VanillaEntitySpawnEvent(Entity entity, Cancellable cancel) {
        super(entity);
        this.entity = entity;
        this.cancel = cancel;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return cancel.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        cancel.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new VanillaLocation(entity);
    }
}
