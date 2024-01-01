package dev.neuralnexus.taterlib.neoforge.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.vanilla.entity.VanillaEntity;
import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

import net.minecraft.world.entity.player.Player;

/** NeoForge implementation of {@link EntityEvent}. */
public class NeoForgeEntityEvent implements EntityEvent {
    private final net.neoforged.neoforge.event.entity.EntityEvent event;

    public NeoForgeEntityEvent(net.neoforged.neoforge.event.entity.EntityEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Entity getEntity() {
        if (event.getEntity() instanceof Player) {
            return new VanillaPlayer((Player) event.getEntity());
        } else {
            return new VanillaEntity(event.getEntity());
        }
    }
}
