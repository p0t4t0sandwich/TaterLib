package dev.neuralnexus.taterlib.v1_20.vanilla.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.v1_20.vanilla.entity.VanillaEntity;
import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;

import net.minecraft.world.entity.player.Player;

/** Vanilla implementation of {@link EntityEvent}. */
public class VanillaEntityEvent implements EntityEvent {
    private final net.minecraft.world.entity.Entity entity;

    public VanillaEntityEvent(net.minecraft.world.entity.Entity entity) {
        this.entity = entity;
    }

    /** {@inheritDoc} */
    @Override
    public Entity getEntity() {
        if (this.entity instanceof Player) {
            return new VanillaPlayer((Player) this.entity);
        } else {
            return new VanillaEntity(this.entity);
        }
    }
}
