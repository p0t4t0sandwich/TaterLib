package dev.neuralnexus.taterlib.forge.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;

import net.minecraft.entity.player.EntityPlayer;

/** Forge implementation of {@link EntityEvent}. */
public class ForgeEntityEvent implements EntityEvent {
    private final net.minecraftforge.event.entity.EntityEvent event;

    public ForgeEntityEvent(net.minecraftforge.event.entity.EntityEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Entity getEntity() {
        if (event.entity instanceof EntityPlayer) {
            return new ForgePlayer((EntityPlayer) event.entity);
        } else {
            return new ForgeEntity(event.entity);
        }
    }
}
