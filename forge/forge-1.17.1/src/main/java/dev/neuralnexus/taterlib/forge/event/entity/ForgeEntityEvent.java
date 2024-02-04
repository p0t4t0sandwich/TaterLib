package dev.neuralnexus.taterlib.forge.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;

import net.minecraft.world.entity.player.Player;

/** Forge implementation of {@link EntityEvent}. */
public class ForgeEntityEvent implements EntityEvent {
    private final net.minecraftforge.event.entity.EntityEvent event;

    public ForgeEntityEvent(net.minecraftforge.event.entity.EntityEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Entity getEntity() {
        if (event.getEntity() instanceof Player) {
            return new ForgePlayer((Player) event.getEntity());
        } else {
            return new ForgeEntity(event.getEntity());
        }
    }
}
