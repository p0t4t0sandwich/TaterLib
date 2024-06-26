/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_21.vanilla.event.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.entity.VanillaEntity;
import dev.neuralnexus.taterlib.v1_21.vanilla.player.VanillaPlayer;

import net.minecraft.world.entity.player.Player;

/** Vanilla implementation of {@link EntityEvent}. */
public class VanillaEntityEvent implements EntityEvent {
    private final net.minecraft.world.entity.Entity entity;

    public VanillaEntityEvent(net.minecraft.world.entity.Entity entity) {
        this.entity = entity;
    }

    /** {@inheritDoc} */
    @Override
    public Entity entity() {
        if (this.entity instanceof Player) {
            return new VanillaPlayer((Player) this.entity);
        } else {
            return new VanillaEntity(this.entity);
        }
    }
}
