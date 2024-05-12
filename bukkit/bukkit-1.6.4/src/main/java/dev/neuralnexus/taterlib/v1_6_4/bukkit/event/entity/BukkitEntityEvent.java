package dev.neuralnexus.taterlib.v1_6_4.bukkit.event.entity;

import dev.neuralnexus.taterlib.v1_6_4.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.entity.EntityEvent;

import org.bukkit.entity.Player;

/** Bukkit implementation of {@link EntityEvent}. */
public class BukkitEntityEvent implements EntityEvent {
    private final org.bukkit.event.entity.EntityEvent event;

    BukkitEntityEvent(org.bukkit.event.entity.EntityEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Entity entity() {
        if (event.getEntity() instanceof Player) {
            return new BukkitPlayer((Player) event.getEntity());
        } else {
            return new BukkitEntity(event.getEntity());
        }
    }
}