/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_10_2.vanilla.event.entity;

import dev.neuralnexus.taterapi.event.Cancellable;
import dev.neuralnexus.taterapi.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_10_2.vanilla.world.VanillaLocation;

import net.minecraft.entity.Entity;

/** Vanilla implementation of {@link EntitySpawnEvent}. */
public class VanillaEntitySpawnEvent extends VanillaEntityEvent implements EntitySpawnEvent {
    private final Entity entity;
    private final Cancellable cancel;

    public VanillaEntitySpawnEvent(Entity entity, Cancellable cancel) {
        super(entity);
        this.entity = entity;
        this.cancel = cancel;
    }

    @Override
    public boolean cancelled() {
        return this.cancel.cancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancel.setCancelled(cancelled);
    }

    @Override
    public Location location() {
        return new VanillaLocation(this.entity);
    }
}
