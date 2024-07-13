/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_14.fabric.event.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.v1_14.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.v1_14.fabric.entity.player.FabricPlayer;

import net.minecraft.entity.player.PlayerEntity;

/** The Fabric implementation of {@link EntityEvent}. */
public class FabricEntityEvent implements EntityEvent {
    private final net.minecraft.entity.Entity entity;

    public FabricEntityEvent(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public Entity entity() {
        if (this.entity instanceof PlayerEntity) {
            return new FabricPlayer((PlayerEntity) this.entity);
        } else {
            return new FabricEntity(this.entity);
        }
    }
}
