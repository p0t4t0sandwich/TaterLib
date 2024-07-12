/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13_2.bukkit.event.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.event.entity.EntityEvent;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.player.BukkitPlayer;

import org.bukkit.entity.Player;

/** Bukkit implementation of {@link EntityEvent}. */
public class BukkitEntityEvent implements EntityEvent {
    private final org.bukkit.event.entity.EntityEvent event;

    BukkitEntityEvent(org.bukkit.event.entity.EntityEvent event) {
        this.event = event;
    }

    @Override
    public Entity entity() {
        if (event.getEntity() instanceof Player) {
            return new BukkitPlayer((Player) event.getEntity());
        } else {
            return new BukkitEntity(event.getEntity());
        }
    }
}
