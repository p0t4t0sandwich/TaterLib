/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.forge.event.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.v1_8_9.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.v1_8_9.forge.entity.player.ForgePlayer;

import net.minecraft.entity.player.EntityPlayer;

/** Forge implementation of {@link EntityEvent}. */
public class ForgeEntityEvent implements EntityEvent {
    private final net.minecraftforge.event.entity.EntityEvent event;

    public ForgeEntityEvent(net.minecraftforge.event.entity.EntityEvent event) {
        this.event = event;
    }

    @Override
    public Entity entity() {
        if (event.entity instanceof EntityPlayer) {
            return new ForgePlayer((EntityPlayer) event.entity);
        } else {
            return new ForgeEntity(event.entity);
        }
    }
}
