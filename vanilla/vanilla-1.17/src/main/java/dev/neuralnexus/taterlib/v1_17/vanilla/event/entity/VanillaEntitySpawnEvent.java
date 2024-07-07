/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_17.vanilla.event.entity;

import dev.neuralnexus.taterapi.event.Cancellable;
import dev.neuralnexus.taterapi.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_17.vanilla.world.VanillaLocation;

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
    public boolean cancelled() {
        return cancel.cancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        cancel.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new VanillaLocation(entity);
    }
}
