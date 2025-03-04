/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.fabric.event.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.WrappedEntity;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;

import net.minecraft.entity.player.EntityPlayer;

/** The Fabric implementation of {@link EntityEvent}. */
public class FabricEntityEvent implements EntityEvent {
    private final net.minecraft.entity.Entity entity;

    public FabricEntityEvent(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public Entity entity() {
        if (this.entity instanceof EntityPlayer) {
            return new WrappedPlayer((EntityPlayer) this.entity);
        } else {
            return new WrappedEntity(this.entity);
        }
    }
}
