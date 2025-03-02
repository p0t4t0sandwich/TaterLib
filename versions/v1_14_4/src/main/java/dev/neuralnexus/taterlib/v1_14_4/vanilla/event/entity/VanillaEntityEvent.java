/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.event.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.event.entity.EntityEvent;

/** Vanilla implementation of {@link EntityEvent}. */
public class VanillaEntityEvent implements EntityEvent {
    private final net.minecraft.world.entity.Entity entity;

    public VanillaEntityEvent(net.minecraft.world.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public Entity entity() {
        return (Entity) entity;
    }
}
